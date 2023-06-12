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

    private final UserRepository userRepository;
    @Autowired
    private final GuRepository guRepository;
    @Autowired
    private final DistrictRepository districtRepository;
    @Autowired
    private final SurveyMoodRepository surveyMoodRepository;

    private final SurveyAdvantageRepository surveyAdvantageRepository;

    private final SurveyDisadvantageRepository surveyDisadvantageRepository;





    public SurveyController(UserRepository userRepository, GuRepository guRepository, DistrictRepository districtRepository, SurveyMoodRepository surveyMoodRepository, SurveyAdvantageRepository surveyAdvantageRepository, SurveyDisadvantageRepository surveyDisadvantageRepository) {
        this.userRepository = userRepository;
        this.guRepository = guRepository;
        this.districtRepository = districtRepository;
        this.surveyMoodRepository = surveyMoodRepository;
        this.surveyAdvantageRepository = surveyAdvantageRepository;
        this.surveyDisadvantageRepository = surveyDisadvantageRepository;
    }


    @PostMapping("/surveyAnswer")
    public ResponseEntity<?> survey(@RequestBody SurveyDTO surveyDTO) {
        try {
            UserEntity userEntity = userRepository.findByEmail(surveyDTO.getUserEmail());
            DistrictEntity districtEntity = districtRepository.findByDistrictName(surveyDTO.getDistrict());
            Role recommendRole;

            if (surveyDTO == null || surveyDTO.getUserEmail() == null) {
                throw new RuntimeException("Survey Response Not Received");
            } else if (userEntity == null) {
                throw new RuntimeException("Can't find User info");
            }

            if (districtEntity==null){
                throw new RuntimeException("doesn't exist Area");
            }

            /*
            if (surveyDTO.getIsFemale()==true) {
                recommendRole = Role.FEMALE;
            } else recommendRole = Role.MALE;
            */

            String gptMakeReview = chatGPTService.getGptMakeReview(surveyDTO);
            /*

            String additionalReview = surveyDTO.getReview() + " "
                    + surveyDTO.getRecommendAge() + " " + recommendRole.getTitle() + "이고, "
                    + surveyDTO.getRecommendHousing() + " 주거 형태를 찾는다면 추천합니다.";
            */

            SurveyEntity survey = SurveyEntity.builder()
                    .user(userEntity)
                    .userEmail(surveyDTO.getUserEmail())
                    .district(districtEntity)
                    .mood(surveyDTO.getMood())
                    .advantage(surveyDTO.getAdvantage())
                    .disadvantage(surveyDTO.getDisadvantage())
                    //.recommendGender(recommendRole)
                    //.recommendHousing(surveyDTO.getRecommendHousing())
                    //.recommendAge(surveyDTO.getRecommendAge())
                    .star(surveyDTO.getStar())
                    .age(surveyDTO.getAge())
                    .review(surveyDTO.getReview())
                    .totalReview(gptMakeReview + surveyDTO.getReview())
                    .build();
            SurveyEntity registerSurvey = surveyService.createSurveyAnswer(survey);

            SurveyDTO responseSurveyDTO = SurveyDTO.builder()
                    .userEmail(registerSurvey.getUser().getEmail())
                    .district(registerSurvey.getDistrictEntity().getDistrictName())
                    .mood(registerSurvey.getMood())
                    .advantage(registerSurvey.getAdvantage())
                    .disadvantage(registerSurvey.getDisadvantage())
                    //.recommendGender(registerSurvey.getRecommendGender())
                    //.recommendHousing(registerSurvey.getRecommendHousing())
                    //.recommendAge(registerSurvey.getAge())
                    .star(registerSurvey.getStar())
                    .age(registerSurvey.getAge())
                    .review(registerSurvey.getReview())
                    .gptReview(registerSurvey.getGptReview())
                    .totalReview(registerSurvey.getTotalReview())
                    .build();

            return ResponseEntity.ok(responseSurveyDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @GetMapping("/districtNames")
    public ResponseEntity<?> getDongNames() {
        log.info("DistrictNames called");


        return ResponseEntity.ok(surveyService.getGuAndDistrict());
    }


    @GetMapping("/guNames")
    public ResponseEntity<?> getGuNames() {
        log.info("DistrictNames called");
        return ResponseEntity.ok(surveyService.guNames().stream().sorted());
    }


    @GetMapping("/moodEntities")
    public ResponseEntity<?> getMoods() {
        log.info("mood called");
        return ResponseEntity.ok(surveyMoodRepository.findAll());
    }

    @GetMapping("/disadvantageEntities")
    public ResponseEntity<?> getDisadvantages() {
        log.info("disadvantage called");
        return ResponseEntity.ok(surveyDisadvantageRepository.findAll());
    }

    @GetMapping("/advantageEntities")
    public ResponseEntity<?> getAdvantages() {
        log.info("advantage called");
        return ResponseEntity.ok(surveyAdvantageRepository.findAll());
    }


}
