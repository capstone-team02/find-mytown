package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuAndDistrictEntity;
import com.team2.findmytown.domain.entity.SurveyEntity;
import com.team2.findmytown.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, String> {
    //List<SurveyEntity> findAllByDistrictEntity(DistrictEntity districtId);

    List<SurveyEntity> findAllByDistrictEntity(GuAndDistrictEntity guAndDistrictEntity);
    SurveyEntity findAllByUserEmail(String email);

}
