package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.dto.request.SurveyDTO;
import com.team2.findmytown.domain.entity.SurveyEntity;

public interface SurveyService {

    public SurveyEntity createSurveyAnswer(final SurveyEntity surveyEntity);

    public DistrictEntity findDistrictbyName(final String districtName);

}
