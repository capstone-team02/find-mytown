package com.team2.findmytown.controller;

import com.team2.findmytown.dto.request.MapDistrictDTO;
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
    public ResponseEntity<?> importTotalReview (@RequestBody MapDistrictDTO districtName){
        return ResponseEntity.ok(surveyService.getReviewList(districtName.getDistrict()));
    }

    @PostMapping("/districtKeyword")
    public ResponseEntity<?> importDistrictKeyword (@RequestBody MapDistrictDTO districtName){
        return ResponseEntity.ok(surveyService.getDistrictKeyword(districtName.getDistrict()));
    }

    @PostMapping("/realEstateByDistrict")
    public ResponseEntity<?> importRealEstate (@RequestBody MapDistrictDTO districtName){
        System.out.println("param : "  + districtName);
        return ResponseEntity.ok(dataService.getRealEstateList(districtName.getDistrict()));
    }

    @PostMapping("/bookmark")
    public ResponseEntity<?> scrapDistrict (@RequestBody Map<String, String> districtName){
        return ResponseEntity.ok(userService.bookmarkDistrict(districtName.get("district")));
    }
}
