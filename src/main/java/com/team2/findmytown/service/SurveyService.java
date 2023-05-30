package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import com.team2.findmytown.domain.entity.SurveyEntity;

public interface SurveyService {

    public SurveyEntity createSurveyAnswer(final SurveyEntity surveyEntity);

    public DistrictEntity findDistrictbyName(final String districtName);

    public GuEntity findGuEntity(final String guName);

}
