package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import com.team2.findmytown.domain.entity.SurveyEntity;
import com.team2.findmytown.domain.repository.DistrictRepository;
import com.team2.findmytown.domain.repository.GuRepository;
import com.team2.findmytown.domain.repository.SurveyRepository;
import com.team2.findmytown.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SurveyServiceImpl implements SurveyService{

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private GuRepository guRepository;


    @Override
    public SurveyEntity createSurveyAnswer(SurveyEntity surveyEntity) {
        if(surveyEntity == null){
            throw new RuntimeException("Fill The Answers.");
        }
        if(!userRepository.existsById(surveyEntity.getUser().getId())){
            throw new RuntimeException("Can't Find User Info");
        }
        return surveyRepository.save(surveyEntity);
    }

    @Override
    public DistrictEntity findDistrictbyName(String districtName) {
        return null;
    }

    public DistrictEntity findDistrictEntity(String districtName){
        return districtRepository.findByDistrictName(districtName);
    }

    public GuEntity findGuEntity(String guName){
        return guRepository.getByGuName(guName);
    }

}
