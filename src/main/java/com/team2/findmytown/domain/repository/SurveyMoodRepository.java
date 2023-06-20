package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.survey.SurveyMoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyMoodRepository extends JpaRepository<SurveyMoodEntity, Integer> {
    SurveyMoodEntity findByMoodKor(String district);
}
