package com.team2.findmytown.controller;

import com.team2.findmytown.service.DataServiceImpl;
import com.team2.findmytown.service.SurveyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/mytown/v1/mainMap")
public class MainMapController {

    @Autowired
    private SurveyServiceImpl surveyService;

    @Autowired
    private DataServiceImpl dataService;

    @PostMapping("/totalReview")
    public ResponseEntity<?> importTotalReview (@RequestParam(value = "district", required = false) String districtName){
        return ResponseEntity.ok(surveyService.getReviewList(districtName));
    }

    @PostMapping("/districtKeyword")
    public ResponseEntity<?> importDistrictKeyword (@RequestParam(value = "district", required = false) String districtName){
        return ResponseEntity.ok(surveyService.getDistrictKeyword(districtName));
    }

    @PostMapping("/realEstateByDistrict")
    public ResponseEntity<?> importRealEstate (@RequestParam(value = "district", required = false) String districtName){
        return ResponseEntity.ok(dataService.getRealEstateList(districtName));
    }
}
