package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, String> {
}
