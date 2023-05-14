package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.PopulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopulationRepository extends JpaRepository<PopulationEntity, Long> {

    @Query(value = "SELECT p FROM PopulationEntity  p ORDER BY p.density DESC ")
    List<PopulationEntity> findDensitysOrderedByDescending();
}
