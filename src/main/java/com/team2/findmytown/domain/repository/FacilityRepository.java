package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.FacilityEntity;
import com.team2.findmytown.domain.entity.PopulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, String> {
    @Query(value = "SELECT f FROM FacilityEntity  f ORDER BY f.bank DESC ")
    List<FacilityEntity> findFacilityEntitiesByDescending();

}
