package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.SurveyEntity;
import com.team2.findmytown.dto.response.ReviewListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, String> {
    public List<SurveyEntity> findAll();
}
