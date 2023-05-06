package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.PopulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationRepository extends JpaRepository<PopulationEntity, Long> {
}
