package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import com.team2.findmytown.domain.repository.DistrictRepository;
import com.team2.findmytown.domain.repository.GuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    private final GuRepository guRepository;
    private final DistrictRepository districtRepository;

    @Autowired
    public DataServiceImpl(GuRepository guRepository, DistrictRepository districtRepository) {
        this.guRepository = guRepository;
        this.districtRepository = districtRepository;
    }

    //구 데이터 저장
    @Override
    public GuEntity findOrCreateNew(GuEntity guEntity) {
        return guRepository.findByGuName(guEntity.getGuName())
                .orElseGet(() -> guRepository.save(guEntity));

    }

    //동 데이터 저장
    @Override
    public List<DistrictEntity> createDistrict(DistrictEntity districtEntity) {
        districtRepository.save(districtEntity);
        log.info("Entity Code : {} is saved",districtEntity.getDistrictId());
        return districtRepository.findAllByDistrictName(districtEntity.getDistrictName());

    }
}
