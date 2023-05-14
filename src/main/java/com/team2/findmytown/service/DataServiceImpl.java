package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.*;
import com.team2.findmytown.domain.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    private final GuRepository guRepository;
    private final DistrictRepository districtRepository;
    private final PopulationRepository populationRepository;
    private final FacilityRepository facilityRepository;
    private final MedicalRepository medicalRepository;

   @Autowired
    public DataServiceImpl(GuRepository guRepository, DistrictRepository districtRepository, PopulationRepository populationRepository, FacilityRepository facilityRepository, MedicalRepository medicalRepository) {
        this.guRepository = guRepository;
        this.districtRepository = districtRepository;
        this.populationRepository = populationRepository;
        this.facilityRepository = facilityRepository;
        this.medicalRepository = medicalRepository;
    }




    //구 데이터 저장
    @Override
    public GuEntity findOrCreateNew(GuEntity guEntity) {
        return guRepository.findByGuName(guEntity.getGuName())
                .orElseGet(() -> guRepository.save(guEntity));

    }

    public GuEntity findGu(String guName){
       return guRepository.getByGuName(guName);
    }

    public MedicalEntity createMedical(MedicalEntity medicalEntity){
       medicalRepository.save(medicalEntity);
       return medicalRepository.findByHospital(medicalEntity.getHospital());
    }

    //동 데이터 저장
    @Override
    public List<DistrictEntity> createDistrict(DistrictEntity districtEntity) {
       PopulationEntity populationEntity = districtEntity.getPopulationEntity();
        populationRepository.save(populationEntity);
       DistrictEntity savedDistrict =  districtRepository.save(districtEntity);
       populationEntity.setDistrictEntity(savedDistrict);

        populationRepository.save(populationEntity);

        log.info("Entity Code : {} is saved",districtEntity.getDistrictId());
        return districtRepository.findAllByDistrictName(districtEntity.getDistrictName());

    }

    @Override
    public DistrictEntity findDistractEntity(GuEntity guEntity,String dongName) {
      return districtRepository.findDistinctByGuEntityAndDistrictName(guEntity,dongName);
    }

    @Override
    public DistrictEntity updateFacilityDistractEntity(DistrictEntity districtEntity, FacilityEntity facilityEntity) {

       facilityRepository.save(facilityEntity);
       DistrictEntity savedDistrict = DistrictEntity.builder()
               .districtId(districtEntity.getDistrictId())
               .facilityEntity(facilityEntity)
               .districtName(districtEntity.getDistrictName())
               .populationEntity(districtEntity.getPopulationEntity())
               .guEntity(districtEntity.getGuEntity())
               .build();
       districtRepository.save(savedDistrict);

       return savedDistrict;
    }



    @Override
    public List<PopulationEntity> createPopulation(PopulationEntity populationEntity) {

       return null;
    }
}
