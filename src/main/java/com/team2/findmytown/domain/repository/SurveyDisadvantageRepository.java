package com.team2.findmytown.domain.repository;


import com.team2.findmytown.domain.entity.SurveyDisadvantageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyDisadvantageRepository extends JpaRepository<SurveyDisadvantageEntity, Integer> {
}
