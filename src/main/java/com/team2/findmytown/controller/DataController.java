package com.team2.findmytown.controller;


import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.FacilityEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import com.team2.findmytown.domain.entity.PopulationEntity;
import com.team2.findmytown.service.DataServiceImpl;
import com.team2.findmytown.service.SurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/addData")
public class DataController {

    private final DataServiceImpl dataService;
    private final SurveyService surveyService;
    @Autowired
    public DataController(DataServiceImpl dataService, SurveyService surveyService){
        this.dataService = dataService;
        this.surveyService = surveyService;
    }


    @GetMapping("/add-table")
    public ResponseEntity<?> addDistrict() throws IOException {
        surveyService.putGuAndDistrict();

        //인구데이터 적재
        File csv = new File("src/main/resources/동별 연령대 리스트.csv");
       try( BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(csv)))){

        String line = "";
        boolean skipFirstLine = true;

        List<String> areaList = new ArrayList<String>();
        List<String> districtList = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            if (skipFirstLine) {
                skipFirstLine = false;
                continue;
            }
            String[] data = line.split(",");
            areaList.add(data[0]);
            districtList.add(data[1]);

            GuEntity newareaEntity = GuEntity.builder()
                    .guName(data[0].toString())
                    .build();
            GuEntity guEntity = dataService.findOrCreateNew(newareaEntity);


            PopulationEntity populationEntity = PopulationEntity.builder()
                    .children(Long.parseLong(data[2]))
                    .teen(Long.parseLong(data[3]))
                    .twenty(Long.parseLong(data[4]))
                    .thirty(Long.parseLong(data[5]))
                    .fourty(Long.parseLong(data[6]))
                    .fifSix(Long.parseLong(data[7]))
                    .elder(Long.parseLong(data[8]))
                    .forign(Long.parseLong(data[9]))
                    .density(Long.parseLong(data[10]))
                    .build();

            DistrictEntity districtEntity = DistrictEntity.builder()
                    .districtName(data[1].toString())
                    .guEntity(guEntity)
                    .facilityEntity(null)
                    .populationEntity(populationEntity)
                    .build();


            List<DistrictEntity> districtEntities = dataService.createDistrict(districtEntity);
        }

       }catch (IOException e) {
           e.printStackTrace();
       }

       //시설 데이터
        csv = new File("src/main/resources/시설리스트.csv");
        try( BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(csv)))){

            String line = "";
            boolean skipFirstLine = true;

            List<String> areaList = new ArrayList<String>();
            List<String> districtList = new ArrayList<String>();

            while ((line = br.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue;
                }
                String[] data2 = line.split(",");
                areaList.add(data2[0]);
                districtList.add(data2[1]);

                GuEntity guEntity = dataService.findGu(data2[0]);

                DistrictEntity districtEntity = dataService.findDistractEntity(guEntity,data2[1]);


                FacilityEntity facilityEntity = FacilityEntity.builder()
                        .parking(Integer.parseInt(data2[2]))
                        .bank(Integer.parseInt(data2[3]))
                        .childcare(Integer.parseInt(data2[4]))
                        .education(Integer.parseInt(data2[5]))
                        .culture(Integer.parseInt(data2[6]))
                        .shoppingCenter(Integer.parseInt(data2[7]))
                        .build();

                dataService.updateFacilityDistractEntity(districtEntity,facilityEntity);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

        //식당 데이터 적재
        File restList = new File("src/main/resources/식당 리스트.csv");
        try( BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(restList)))){

            String line = "";
            boolean skipFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue;
                }
                String[] data3 = line.split(",");

                GuEntity guEntity = dataService.findGu(data3[0]);

                DistrictEntity districtEntity = dataService.findDistractEntity(guEntity,data3[1]);

                FacilityEntity facilityEntity = districtEntity.getFacilityEntity();
                facilityEntity.setRestaurant(Integer.parseInt(data3[2]));
                facilityEntity.setCafe(Integer.parseInt(data3[3]));

                dataService.updateFacilityDistractEntity(districtEntity,facilityEntity);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }







        return ResponseEntity.ok().build();

    }
}
