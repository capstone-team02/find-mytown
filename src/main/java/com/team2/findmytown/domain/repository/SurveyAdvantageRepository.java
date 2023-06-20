package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.survey.SurveyAdvantageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyAdvantageRepository extends JpaRepository<SurveyAdvantageEntity, Integer> {
    SurveyAdvantageEntity findByAdvantageKor(String district_name);

}
