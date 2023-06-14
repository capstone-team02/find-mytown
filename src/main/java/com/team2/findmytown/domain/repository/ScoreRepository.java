package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity,Long> {
    ScoreEntity findByDistrictEntity(DistrictEntity districtEntity);
}
