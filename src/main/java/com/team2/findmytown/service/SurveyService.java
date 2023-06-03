package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import com.team2.findmytown.domain.entity.SurveyEntity;
import com.team2.findmytown.dto.request.DistrictNameDTO;

import java.util.List;
import java.util.Map;

public interface SurveyService {

    public SurveyEntity createSurveyAnswer(final SurveyEntity surveyEntity);

    public DistrictEntity findDistrictbyName(final String districtName);

    public GuEntity findGuEntity(final String guName);
    public void putGuAndDistrict();

    public List<String> guNames();

    public List<DistrictNameDTO> getGuAndDistrict();
}
