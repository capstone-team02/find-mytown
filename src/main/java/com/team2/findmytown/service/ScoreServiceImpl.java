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

import java.util.List;

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

        int rank = 1;

        for(PopulationEntity density :recordList ){
            ScoreEntity scoreEntity = new ScoreEntity();
            scoreEntity.setDensityRank(rank++);
            DistrictEntity districtEntity = density.getDistrictEntity();
            districtEntity.setScoreEntity(scoreEntity);

            scoreRepository.save(scoreEntity);
            districtRepository.save(districtEntity);

        }

        rank = 1;
        recordList = populationRepository.findChildrensOrderedByDescending();
        for(PopulationEntity chilsren : recordList){

        }




    }


}
