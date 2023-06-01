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

    @Query(value = "SELECT p FROM PopulationEntity  p ORDER BY p.children DESC ")
    List<PopulationEntity> findChildrensOrderedByDescending();

    @Query(value = "SELECT p FROM PopulationEntity  p ORDER BY p.teen DESC ")
    List<PopulationEntity> findTeensOrderedByDescending();

    @Query(value = "SELECT p FROM PopulationEntity  p ORDER BY p.twenty DESC ")
    List<PopulationEntity> findTwentysOrderedByDescending();

    @Query(value = "SELECT p FROM PopulationEntity  p ORDER BY p.thirty DESC ")
    List<PopulationEntity> findThirtysOrderedByDescending();

    @Query(value = "SELECT p FROM PopulationEntity  p ORDER BY p.fourty DESC ")
    List<PopulationEntity> findFourtysOrderedByDescending();

    @Query(value = "SELECT p FROM PopulationEntity  p ORDER BY p.fifSix DESC ")
    List<PopulationEntity> findFifSixsOrderedByDescending();

    @Query(value = "SELECT p FROM PopulationEntity  p ORDER BY p.elder DESC ")
    List<PopulationEntity> findElderOrderedByDescending();

    @Query(value = "SELECT p FROM PopulationEntity  p ORDER BY p.foriegn DESC ")
    List<PopulationEntity> findForignsOrderedByDescending();



}
