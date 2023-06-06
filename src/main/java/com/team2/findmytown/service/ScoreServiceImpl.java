package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.FacilityEntity;
import com.team2.findmytown.domain.entity.PopulationEntity;
import com.team2.findmytown.domain.entity.ScoreEntity;
import com.team2.findmytown.domain.repository.*;
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
    private final FacilityRepository facilityRepository;

    private final MedicalRepository medicalRepository;

    @Autowired
    public ScoreServiceImpl(PopulationRepository populationRepository, ScoreRepository scoreRepository,DistrictRepository districtRepository, FacilityRepository facilityRepository,MedicalRepository medicalRepository) {
        this.populationRepository = populationRepository;
        this.scoreRepository = scoreRepository;
        this.districtRepository = districtRepository;
        this.facilityRepository = facilityRepository;
        this.medicalRepository = medicalRepository;
    }

    @Override
    public void calculateAndSaveScores() {
        List<PopulationEntity> recordList = populationRepository.findDensitysOrderedByDescending();
        List<ScoreEntity> scoreEntities = new ArrayList<>();
        List<FacilityEntity> facilityEntities = facilityRepository.findFacilityEntitiesByDescending();
        int populationRank = 1;
        int facilityRank = 1;


        //List<ScoreEntity> scoreEntities = new ArrayList<>();

        for (PopulationEntity density : recordList) {
            ScoreEntity scoreEntity = new ScoreEntity();
            scoreEntity.setDensityRank(populationRank++);
            setPopulationRankValues(scoreEntity, density);
            scoreEntities.add(scoreEntity);

            DistrictEntity districtEntity = density.getDistrictEntity();
            districtEntity.setScoreEntity(scoreEntity);
        }

        scoreRepository.saveAll(scoreEntities);
        districtRepository.saveAll(recordList.stream().map(PopulationEntity::getDistrictEntity).collect(Collectors.toList()));

        for (FacilityEntity facility : facilityEntities) {
            DistrictEntity districtEntity = facility.getDistrictEntity();
            ScoreEntity findScoreEntity = districtEntity.getScoreEntity();

            if (findScoreEntity == null) {
                findScoreEntity = new ScoreEntity();
                districtEntity.setScoreEntity(findScoreEntity);
            }

            findScoreEntity.setBankRank(facilityRank++);
            // 다른 랭크 값들도 설정해줘야 함

            scoreRepository.save(findScoreEntity);
            districtRepository.save(districtEntity);
        }



    }




    private void setPopulationRankValues(ScoreEntity scoreEntity, PopulationEntity populationEntity) {
        try {
            Field[] fields = ScoreEntity.class.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().endsWith("Rank")) {
                    field.setAccessible(true);
                    int rankValue = getPopulationRankValue(field.getName(), populationEntity);
                    field.set(scoreEntity, rankValue);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
/*
    private void setFacilityRankValues(ScoreEntity scoreEntity, FacilityEntity facilityEntity) {
        try {
            Field[] fields = ScoreEntity.class.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().endsWith("Rank")) {
                    field.setAccessible(true);
                    int rankValue = getFacilityRankValue(field.getName(), populationEntity);
                    field.set(scoreEntity, rankValue);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

 */
    private int getFacilityRankValue(String fieldName, PopulationEntity populationEntity) {
        switch (fieldName) {
            case "densityRank":
                // 밀집도에 대한 순위 계산 로직 추가
                return calculateDensityRank(populationEntity);
            case "childrenRank":
                // 어린이에 대한 순위 계산 로직 추가
                return calculateChildrenRank(populationEntity);
            case "teenRank":
                // 어린이에 대한 순위 계산 로직 추가
                return calculateTeenRank(populationEntity);
            case "twentyRank":
                return calculateTwentyRank(populationEntity);
            case "thirtyRank":
                return calculateThirtyRank(populationEntity);
            case "fourtyRank":
                return calculateFourtyRank(populationEntity);
            case "fifSixRank":
                return calculateFifSix(populationEntity);
            case "elderRank":
                return calculateElder(populationEntity);
            case "foriegnRank":
                return calculateForiegn(populationEntity);



            default:
                return 0;
        }
    }

    private int getPopulationRankValue(String fieldName, PopulationEntity populationEntity) {
        switch (fieldName) {
            case "densityRank":
                // 밀집도에 대한 순위 계산 로직 추가
                return calculateDensityRank(populationEntity);
            case "childrenRank":
                // 어린이에 대한 순위 계산 로직 추가
                return calculateChildrenRank(populationEntity);
            case "teenRank":
                // 어린이에 대한 순위 계산 로직 추가
                return calculateTeenRank(populationEntity);
            case "twentyRank":
                return calculateTwentyRank(populationEntity);
            case "thirtyRank":
                return calculateThirtyRank(populationEntity);
            case "fourtyRank":
                return calculateFourtyRank(populationEntity);
            case "fifSixRank":
                return calculateFifSix(populationEntity);
            case "elderRank":
                return calculateElder(populationEntity);
            case "foriegnRank":
                return calculateForiegn(populationEntity);



            default:
                return 0;
        }
    }

    //인구 밀집도
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
    //아이들
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
    //10대
    private int calculateTeenRank(PopulationEntity populationEntity) {
        Long teenCount = populationEntity.getTeen();
        List<PopulationEntity> populationList = populationRepository.findAll();
        int count = 0;
        for (PopulationEntity entity : populationList) {
            if (entity.getTeen() > teenCount) {
                count++;
            }
        }
        return count + 1; // 10대 인구 수가 많을수록 순위가 높아집니다.
    }

    //20대
    private int calculateTwentyRank(PopulationEntity populationEntity) {
        Long twentyCount = populationEntity.getTwenty();
        List<PopulationEntity> populationList = populationRepository.findAll();
        int count = 0;
        for (PopulationEntity entity : populationList) {
            if (entity.getTwenty() > twentyCount) {
                count++;
            }
        }
        return count + 1; // 20대 인구 수가 많을수록 순위가 높아집니다.
    }


    //30대
    private int calculateThirtyRank(PopulationEntity populationEntity) {
        Long thirtyCount = populationEntity.getThirty();
        List<PopulationEntity> populationList = populationRepository.findAll();
        int count = 0;
        for (PopulationEntity entity : populationList) {
            if (entity.getThirty() > thirtyCount) {
                count++;
            }
        }
        return count + 1; // 20대 인구 수가 많을수록 순위가 높아집니다.
    }

    //40대
    private int calculateFourtyRank(PopulationEntity populationEntity) {
        Long fourtyCount = populationEntity.getFourty();
        List<PopulationEntity> populationList = populationRepository.findAll();
        int count = 0;
        for (PopulationEntity entity : populationList) {
            if (entity.getFourty() > fourtyCount) {
                count++;
            }
        }
        return count + 1; // 20대 인구 수가 많을수록 순위가 높아집니다.
    }
    //5-60대
    private int calculateFifSix(PopulationEntity populationEntity) {
        Long fifSixCount = populationEntity.getFifSix();
        List<PopulationEntity> populationList = populationRepository.findAll();
        int count = 0;
        for (PopulationEntity entity : populationList) {
            if (entity.getFifSix() > fifSixCount) {
                count++;
            }
        }
        return count + 1; // 20대 인구 수가 많을수록 순위가 높아집니다.
    }

    //노인
    private int calculateElder(PopulationEntity populationEntity) {
        Long elderCount = populationEntity.getElder();
        List<PopulationEntity> populationList = populationRepository.findAll();
        int count = 0;
        for (PopulationEntity entity : populationList) {
            if (entity.getElder() > elderCount) {
                count++;
            }
        }
        return count + 1; // 20대 인구 수가 많을수록 순위가 높아집니다.
    }


    //외국인
    private int calculateForiegn(PopulationEntity populationEntity) {
        Long foriegnCount = populationEntity.getForiegn();
        List<PopulationEntity> populationList = populationRepository.findAll();
        int count = 0;
        for (PopulationEntity entity : populationList) {
            if (entity.getForiegn() > foriegnCount) {
                count++;
            }
        }
        return count + 1; // 20대 인구 수가 많을수록 순위가 높아집니다.
    }




}
