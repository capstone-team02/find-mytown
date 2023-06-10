package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.PopulationDensityEntity;
import com.team2.findmytown.domain.entity.PopulationEntity;
import com.team2.findmytown.domain.repository.DistrictRepository;
import com.team2.findmytown.domain.repository.PopulationDensityRepository;
import com.team2.findmytown.domain.repository.PopulationRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopulationDensityServiceImpl implements PopulationDensityService {

    private final PopulationDensityRepository populationDensityRepository;

    public PopulationDensityServiceImpl(PopulationDensityRepository populationDensityRepository) {
        this.populationDensityRepository = populationDensityRepository;
    }

    public void insertPopulationDensity() {
        List<PopulationDensityEntity> populationDensityList = new ArrayList<>();
        PopulationDensityEntity populationDensity = new PopulationDensityEntity();
        List<String> dongNameList = new ArrayList<>();
        List<String> populationList = new ArrayList<>();
        List<String> densityList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream("src/main/resources/인구밀도_20230405134931.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int rowIndex = 0;
            int columnIndex = 0;

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();
            for (rowIndex = 3; rowIndex < rows; rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    int cells = row.getPhysicalNumberOfCells();
                    for (columnIndex = 2; columnIndex < cells; columnIndex++) {
                        XSSFCell cell = row.getCell(columnIndex);
                        String value = "";

                        if (cell == null) {
                            continue;
                        } else {
                            switch (cell.getCellType()) {
                                case FORMULA:
                                    value = cell.getCellFormula();
                                    break;
                                case NUMERIC:
                                    value = cell.getNumericCellValue() + "";
                                    break;
                                case STRING:
                                    value = cell.getStringCellValue() + "";
                                    break;
                                case BLANK:
                                    value = cell.getBooleanCellValue() + "";
                                    break;
                                case ERROR:
                                    value = cell.getErrorCellValue() + "";
                                    break;
                            }
                        }
                        if (value.equals("소계")) {
                            break;
                        }
                        if (columnIndex == 2) {
                            dongNameList.add(value);
                        }
                        if (columnIndex == 3) {
                            populationList.add(value);
                        }
                        if (columnIndex == 4) {
                            densityList.add(value);
                        }
                    }

                }
                for (int i = 0; i < dongNameList.size(); i++) {
                    populationDensity = PopulationDensityEntity.builder()
                            .dongName(dongNameList.get(i))
                            .population(populationList.get(i))
                            .density(densityList.get(i))
                            .build();
                }

                populationDensityList.add(populationDensity);
            }
            populationDensityRepository.saveAll(populationDensityList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
