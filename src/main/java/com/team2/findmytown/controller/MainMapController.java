package com.team2.findmytown.controller;


import com.team2.findmytown.dto.response.ReviewListDTO;
import com.team2.findmytown.service.SurveyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/mytown/v1/mainMap")
public class MainMapController {

    @Autowired
    private SurveyServiceImpl surveyService;

    @GetMapping("/totalReview")
    public ResponseEntity<?> importTotalReview (@RequestBody Map<String, String> districtName){
        return ResponseEntity.ok(surveyService.getReviewList(districtName.get("district")));
    }

    @GetMapping("/districtKeyword")
    public ResponseEntity<?> importDistrictKeyword (@RequestBody Map<String, String> distrctName){
        return ResponseEntity.ok(surveyService.getDistrictKeyword(distrctName.get("district")));
    }
}
