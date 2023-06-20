package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.GuAndDistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuAndDistrictRepository extends JpaRepository<GuAndDistrictEntity, Integer> {
    GuAndDistrictEntity findByDistrictName(String districtName);



}
