package com.team2.findmytown.controller;


import com.team2.findmytown.dto.response.ReviewListDTO;
import com.team2.findmytown.service.SurveyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mytown/v1/mainMap")
public class MainMapController {

    @Autowired
    private SurveyServiceImpl surveyService;

    @GetMapping("/TotalReview")
    public ResponseEntity<?> importTotalReview (){
        return ResponseEntity.ok(surveyService.getReviewList());
    }
}
