package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.RealEstateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RealEstateRepository extends JpaRepository<RealEstateEntity, Integer> {
    Optional<RealEstateEntity> findByBuildingName(String building_name);


}
