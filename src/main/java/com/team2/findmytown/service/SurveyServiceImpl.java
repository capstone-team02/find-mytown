package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.*;
import com.team2.findmytown.domain.repository.*;
import com.team2.findmytown.dto.request.DistrictNameDTO;
import com.team2.findmytown.dto.response.ReviewListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public DistrictEntity findDistrict(String districtName) {
        return null;
    }

    @Override
    public UserEntity findUser(String email) {
        return null;
    }

    @Override
    public List<String> findGuNames() {
        return null;
    }


    public DistrictEntity findDistrictbyName(String districtName) {
        return null;
    }

    public DistrictEntity findDistrictEntity(String districtName) {
        return districtRepository.findByDistrictName(districtName);
    }

    public GuEntity findGuEntity(String guName) {
        return guRepository.getByGuName(guName);
    }

    public List<DistrictNameDTO> getGuAndDistrict() {
        List<DistrictEntity> list = districtRepository.findAll();
        List<GuEntity> guIdList = guRepository.findAll();
        List<Long> guId = new ArrayList<>();
        Map<String, List<String>> districtNameList = new HashMap<>();
        List<String> g = new ArrayList();
        List<DistrictNameDTO> result = new ArrayList<>();
        DistrictNameDTO districtNameDTO = null;


        for (int k = 0; k < guIdList.size(); k++) {
            guId.add(guIdList.get(k).getGuId());
        }

        for (int k = 0; k < guId.size(); k++) {

            System.out.println("k " + k);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getGuEntity().getGuId() == guId.get(k) || list.get(i).getGuEntity().getGuId().equals(guId.get(k))) {
                    g.add(list.get(i).getDistrictName());
                }
            }
            districtNameList.put(guRepository.findAllByGuId(guId.get(k)).getGuName(), g);

            if (k != 24) {
                g = new ArrayList<>();
            }

        }
        for (String s : districtNameList.keySet()) {
            districtNameDTO = DistrictNameDTO
                    .builder()
                    .guName(s)
                    .dongName(districtNameList.get(s))
                    .build();
            result.add(districtNameDTO);
        }



        return result;
    }



    //
    public List<String> guNames() {
        List<GuEntity> gu = new ArrayList<>();
        List<String> guNames = new ArrayList<>();

        gu = guRepository.findAll();

        DistrictNameDTO districtNameDTO;
        List<DistrictNameDTO> districtNameList = new ArrayList<>();
        for (int i = 0; i < gu.size(); i++) {
            guNames.add(gu.get(i).getGuName().toString());
        }

        return guNames;
    }
//    public List<String> findDongNames(){
//
//    }


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

    public List<String> getDistrictKeyword(String district) {

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
    }
