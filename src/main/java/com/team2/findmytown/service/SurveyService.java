package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import com.team2.findmytown.domain.entity.SurveyEntity;
import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.dto.request.SurveyDTO;

import java.util.List;

public interface SurveyService {

    public SurveyEntity createSurveyAnswer(final SurveyEntity surveyEntity);
    public DistrictEntity findDistrict(final String districtName);
    public UserEntity findUser(final String email);
    public List<String> findGuNames();
}
