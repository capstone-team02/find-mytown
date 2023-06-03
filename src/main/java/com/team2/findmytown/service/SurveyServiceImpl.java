package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.*;
import com.team2.findmytown.domain.repository.DistrictRepository;
import com.team2.findmytown.domain.repository.GuRepository;
import com.team2.findmytown.domain.repository.SurveyRepository;
import com.team2.findmytown.domain.repository.UserRepository;
import com.team2.findmytown.dto.response.ReviewListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private GuRepository guRepository;
    @Autowired
    private UserServiceImple userService;


    @Override
    public SurveyEntity createSurveyAnswer(SurveyEntity surveyEntity) {
        if (surveyEntity == null) {
            throw new RuntimeException("Fill The Answers.");
        }
        if (!userRepository.existsById(surveyEntity.getUser().getId())) {
            throw new RuntimeException("Can't Find User Info");
        }
        return surveyRepository.save(surveyEntity);
    }

    public DistrictEntity findDistrict(String districtName) {
        return districtRepository.findByDistrictName(districtName);
    }

    public UserEntity findUser(String email) {
        return userRepository.findByEmail(email);
    }

    public List<SurveyEntity> findSurveyByDistrict(String district){
        DistrictEntity districtId = districtRepository.findByDistrictName(district);
        if (districtId == null) {
            throw new RuntimeException("Doesn't exist Review About Request District");
        }

        List<SurveyEntity> surveyEntities = surveyRepository.findAllByDistrictEntity(districtId);
        if (surveyEntities == null) {
            throw new RuntimeException("can't find survey List.");
        }

        return surveyEntities;
    }

    public List<String> findGuNames() {
        List<GuEntity> gu = new ArrayList<>();
        List<String> guNames = new ArrayList<>();
        gu = guRepository.findAll();
        for (int i = 0; i < gu.size(); i++) {
            guNames.add(gu.get(i).getGuName());
        }
        return guNames;
    }

    public List<ReviewListDTO> getReviewList(String district) {

        List<ReviewListDTO> reviewListDTO = new ArrayList<>();
        List<SurveyEntity> surveyEntities = findSurveyByDistrict(district);

        ReviewListDTO reviewDTO;
        String getNickname;
        String getTotalReview;

        for (int i = 0; i < surveyEntities.size(); i++) {
            getNickname = userRepository.findByEmail(surveyEntities.get(0).getUserEmail()).getNickname();
            getTotalReview = surveyEntities.get(i).getTotalReview();

            reviewDTO = ReviewListDTO.builder()
                    .nickname(getNickname)
                    .totalReview(getTotalReview).build();

            reviewListDTO.add(reviewDTO);
        }
        return reviewListDTO;
    }

    public List<String> getDistrictKeyword(String district){

        List<String> keyword = new ArrayList<>();
        List<SurveyEntity> surveyEntities = findSurveyByDistrict(district);

        for (int i = 0; i < surveyEntities.size(); i++) {
            keyword.addAll(surveyEntities.get(i).getMood());
            keyword.addAll(surveyEntities.get(i).getAdvantage());
            keyword.addAll(surveyEntities.get(i).getDisadvantage());
        }

        //중복 제거 후 반환
        return keyword.stream().distinct().collect(Collectors.toList());
        }

    }