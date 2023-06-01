package com.team2.findmytown.controller;

import com.team2.findmytown.domain.entity.*;
import com.team2.findmytown.domain.repository.GuRepository;
import com.team2.findmytown.dto.request.SurveyDTO;
import com.team2.findmytown.dto.response.ResponseDTO;
import com.team2.findmytown.service.ChatGPTService;
import com.team2.findmytown.service.SurveyServiceImpl;
import com.team2.findmytown.service.UserServiceImple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mytown/v1/survey")
public class SurveyController {

    @Autowired
    private ChatGPTService chatGPTService;

    @Autowired
    private SurveyServiceImpl surveyService;

    @Autowired
    private UserServiceImple userService;

    private final GuRepository guRepository;


    public SurveyController(GuRepository guRepository) {
        this.guRepository = guRepository;
    }


    @PostMapping("/surveyAnswer")
    public ResponseEntity<?> survey(@RequestBody SurveyDTO surveyDTO) {
        try {

            UserEntity userEntity = surveyService.findUser(surveyDTO.getUserEmail());
            DistrictEntity districtEntity = surveyService.findDistrict(surveyDTO.getDistrict());
            Role recommendRole;

            if (surveyDTO == null || surveyDTO.getUserEmail() == null) {
                throw new RuntimeException("Survey Response Not Received");
            } else if (userEntity == null) {
                throw new RuntimeException("Can't find User info");
            }

            if (districtEntity==null){
                throw new RuntimeException("doesn't exist Area");
            }

            if (surveyDTO.getIsFemale()==true) {
                recommendRole = Role.FEMALE;
            } else recommendRole = Role.MALE;


            SurveyEntity survey = SurveyEntity.builder()
                    .user(userEntity)
                    .district(districtEntity)
                    .mood(surveyDTO.getMood())
                    .advantage(surveyDTO.getAdvantage())
                    .disadvantage(surveyDTO.getDisadvantage())
                    .recommendGender(recommendRole)
                    .recommendHousing(surveyDTO.getRecommendHousing())
                    .recommendAge(surveyDTO.getRecommendAge())
                    .star(surveyDTO.getStar())
                    .age(surveyDTO.getAge())
                    .review(surveyDTO.getReview())
                    //.gptReview(chatGPTService.getChatMakeReview(surveyDTO))
                    .build();
            SurveyEntity registerSurvey = surveyService.createSurveyAnswer(survey);

            SurveyDTO responseSurveyDTO = SurveyDTO.builder()
                    .userEmail(registerSurvey.getUser().getEmail())
                    .district(registerSurvey.getDistrictEntity().getDistrictName())
                    .mood(registerSurvey.getMood())
                    .advantage(registerSurvey.getAdvantage())
                    .disadvantage(registerSurvey.getDisadvantage())
                    .recommendGender(registerSurvey.getRecommendGender())
                    .recommendHousing(registerSurvey.getRecommendHousing())
                    .recommendAge(registerSurvey.getAge())
                    .star(registerSurvey.getStar())
                    .age(registerSurvey.getAge())
                    .review(registerSurvey.getReview())
                    //.gptReview(registerSurvey.getGptReview())
                    .build();

            return ResponseEntity.ok(responseSurveyDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/districtNames")
    public ResponseEntity<List<String>> getGuNames(){

        return ResponseEntity.ok(surveyService.findGuNames());
    }
}