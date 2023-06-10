package com.team2.findmytown.domain.repository;


import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuRepository extends JpaRepository<GuEntity, Long> {
   Optional<GuEntity> findByGuName(String areaName);
   GuEntity getByGuName(String guName);
   GuEntity findAllByGuId(Long guId);

   List<GuEntity> findGuEntityBy();

}
