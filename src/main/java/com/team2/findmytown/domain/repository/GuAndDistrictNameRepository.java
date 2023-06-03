package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.GuAndDistrictNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuAndDistrictNameRepository extends JpaRepository<GuAndDistrictNameEntity, Integer> {

    List<GuAndDistrictNameEntity> findAllByGuId(Long id);
}

