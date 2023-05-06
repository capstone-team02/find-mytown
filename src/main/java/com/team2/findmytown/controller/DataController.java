package com.team2.findmytown.controller;


import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import com.team2.findmytown.domain.entity.PopulationEntity;
import com.team2.findmytown.service.DataServiceImpl;
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
    @Autowired
    public DataController(DataServiceImpl dataService){
        this.dataService = dataService;
    }


    @GetMapping("/add-table")
    public ResponseEntity<?> addDistrict() throws IOException {
        //csv 파일을 읽어서 행정구역 데이터 적재
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
                    .build();

            DistrictEntity districtEntity = DistrictEntity.builder()
                    .districtName(data[1].toString())
                    .guEntity(guEntity)
                    .populationEntity(populationEntity)
                    .build();



            List<DistrictEntity> districtEntities = dataService.createDistrict(districtEntity);
        }

       }catch (IOException e) {
           e.printStackTrace();
       }

       //설문조사 테이블에 각 항목 적재



        return ResponseEntity.ok().build();

    }
}
