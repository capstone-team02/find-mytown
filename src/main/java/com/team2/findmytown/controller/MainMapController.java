package com.team2.findmytown.controller;

import com.team2.findmytown.service.DataServiceImpl;
import com.team2.findmytown.service.SurveyServiceImpl;
import com.team2.findmytown.service.UserServiceImple;
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
    private UserServiceImple userService;
    @Autowired
    private DataServiceImpl dataService;

    @PostMapping("/totalReview")
    public ResponseEntity<?> importTotalReview (@RequestBody Map<String, String> districtName){
        return ResponseEntity.ok(surveyService.getReviewList(districtName.get("district")));
    }

    @PostMapping("/districtKeyword")
    public ResponseEntity<?> importDistrictKeyword (@RequestBody Map<String, String> distrctName){
        return ResponseEntity.ok(surveyService.getDistrictKeyword(distrctName.get("district")));
    }

    @PostMapping("/realEstateByDistrict")
    public ResponseEntity<?> importRealEstate (@RequestBody Map<String, String> districtName){
        return ResponseEntity.ok(dataService.getRealEstateList(districtName.get("district")));
    }

    @PostMapping("/bookmark")
    public ResponseEntity<?> scrapDistrict (@RequestBody Map<String, String> districtName){
        return ResponseEntity.ok(userService.bookmarkDistrict(districtName.get("district")));
    }
}
