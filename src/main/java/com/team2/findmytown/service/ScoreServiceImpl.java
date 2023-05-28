package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.PopulationEntity;
import com.team2.findmytown.domain.entity.ScoreEntity;
import com.team2.findmytown.domain.repository.DistrictRepository;
import com.team2.findmytown.domain.repository.PopulationRepository;
import com.team2.findmytown.domain.repository.ScoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScoreServiceImpl implements ScoreService {

    private final PopulationRepository populationRepository;
    private final ScoreRepository scoreRepository;

    private final DistrictRepository districtRepository;

    @Autowired
    public ScoreServiceImpl(PopulationRepository populationRepository, ScoreRepository scoreRepository,DistrictRepository districtRepository) {
        this.populationRepository = populationRepository;
        this.scoreRepository = scoreRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public void calculateAndSaveScores() {
        List<PopulationEntity> recordList = populationRepository.findDensitysOrderedByDescending();
        List<ScoreEntity> scoreEntities = new ArrayList<>();
        int rank = 1;

        for (PopulationEntity density : recordList) {
            ScoreEntity scoreEntity = new ScoreEntity();
            scoreEntity.setDensityRank(rank++);
            setRankValues(scoreEntity, density);
            scoreEntities.add(scoreEntity);

            DistrictEntity districtEntity = density.getDistrictEntity();
            districtEntity.setScoreEntity(scoreEntity);
        }

        scoreRepository.saveAll(scoreEntities);
        districtRepository.saveAll(recordList.stream().map(PopulationEntity::getDistrictEntity).collect(Collectors.toList()));




    }

    private void setRankValues(ScoreEntity scoreEntity, PopulationEntity populationEntity) {
        try {
            Field[] fields = ScoreEntity.class.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().endsWith("Rank")) {
                    field.setAccessible(true);
                    int rankValue = getRankValue(field.getName(), populationEntity);
                    field.set(scoreEntity, rankValue);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private int getRankValue(String fieldName, PopulationEntity populationEntity) {
        switch (fieldName) {
            case "densityRank":
                // 밀집도에 대한 순위 계산 로직 추가
                return calculateDensityRank(populationEntity);
            case "childrenRank":
                // 어린이에 대한 순위 계산 로직 추가
                return calculateChildrenRank(populationEntity);

            default:
                return 0;
        }
    }

    private int calculateDensityRank(PopulationEntity populationEntity) {
        Long density = populationEntity.getDensity();
        List<PopulationEntity> populationList = populationRepository.findAll();
        int count = 0;
        for (PopulationEntity entity : populationList) {
            if (entity.getDensity() > density) {
                count++;
            }
        }
        return count + 1; // 밀집도가 높을수록 순위가 높아집니다.
    }
    private int calculateChildrenRank(PopulationEntity populationEntity) {
        Long childrenCount = populationEntity.getChildren();
        List<PopulationEntity> populationList = populationRepository.findAll();
        int count = 0;
        for (PopulationEntity entity : populationList) {
            if (entity.getChildren() > childrenCount) {
                count++;
            }
        }
        return count + 1; // 어린이 수가 많을수록 순위가 높아집니다.
    }

}
