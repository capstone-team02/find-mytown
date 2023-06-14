package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.*;
import com.team2.findmytown.domain.repository.*;
import com.team2.findmytown.dto.response.RealEstateDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    private final GuRepository guRepository;
    private final DistrictRepository districtRepository;
    private final PopulationRepository populationRepository;
    private final FacilityRepository facilityRepository;
    private final MedicalRepository medicalRepository;
    private final RealEstateRepository realEstateRepository;
    private final GuAndDistrictRepository guAndDistrictRepository;

   @Autowired
    public DataServiceImpl(GuRepository guRepository, DistrictRepository districtRepository, PopulationRepository populationRepository,
                           RealEstateRepository realEstateRepository, FacilityRepository facilityRepository, MedicalRepository medicalRepository, GuAndDistrictRepository guAndDistrictRepository) {
        this.guRepository = guRepository;
        this.districtRepository = districtRepository;
        this.populationRepository = populationRepository;
        this.facilityRepository = facilityRepository;
        this.realEstateRepository = realEstateRepository;
        this.medicalRepository = medicalRepository;
       this.guAndDistrictRepository = guAndDistrictRepository;
   }

    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue == null) return null;
        return nValue.getNodeValue();
    }


    public void guAndDistrict() {
        try {
            List<GuAndDistrictEntity> guAndDistrictEntities = new ArrayList<>();
            List<String> buildingName = new ArrayList<>();
            String url = " http://openapi.seoul.go.kr:8088/4b614a6876776c6434326f6544426b/xml/districtEmd/1/424/";
            http:
//openapi.seoul.go.kr:8088/4b614a6876776c6434326f6544426b/xml/districtEmd/1/5/
            System.out.println(url);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            doc.getDocumentElement().normalize();
            System.out.println(doc.getDocumentElement());
            System.out.println("Root element :  " + doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getElementsByTagName("row");


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if (!buildingName.contains(getTagValue("BLDG_NM", element))) {
                        GuAndDistrictEntity guAndDistrictEntity = GuAndDistrictEntity.builder()
                                .gu_name(getTagValue("SGG_NM", element))
                                .district_name(getTagValue("BJDONG_NM", element))
                                .build();


                        guAndDistrictEntities.add(guAndDistrictEntity);

                    }
                }
            }

            guAndDistrictRepository.saveAll(guAndDistrictEntities);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //구 데이터 저장
    @Override
    public GuEntity findOrCreateNew(GuEntity guEntity) {
        return guRepository.findByGuName(guEntity.getGuName())
                .orElseGet(() -> guRepository.save(guEntity));

    }

    public GuEntity findGu(String guName){
       return guRepository.getByGuName(guName);
    }

    public MedicalEntity createMedical(MedicalEntity medicalEntity){
       medicalRepository.save(medicalEntity);
       return medicalRepository.findByHospital(medicalEntity.getHospital());
    }

    //동 데이터 저장
    @Override
    public List<DistrictEntity> createDistrict(DistrictEntity districtEntity) {
        populationRepository.save(districtEntity.getPopulationEntity());
        districtRepository.save(districtEntity);
        log.info("Entity Code : {} is saved",districtEntity.getDistrictId());
        return districtRepository.findAllByDistrictName(districtEntity.getDistrictName());

    }

    @Override
    public DistrictEntity findDistractEntity(GuEntity guEntity,String dongName) {
      return districtRepository.findDistinctByGuEntityAndDistrictName(guEntity,dongName);
    }

    @Override
    public DistrictEntity updateFacilityDistractEntity(DistrictEntity districtEntity, FacilityEntity facilityEntity) {

       facilityRepository.save(facilityEntity);
       DistrictEntity savedDistrict = DistrictEntity.builder()
               .districtId(districtEntity.getDistrictId())
               .facilityEntity(facilityEntity)
               .districtName(districtEntity.getDistrictName())
               .populationEntity(districtEntity.getPopulationEntity())
               .guEntity(districtEntity.getGuEntity())
               .build();
       districtRepository.save(savedDistrict);

       return savedDistrict;
    }



    @Override
    public List<PopulationEntity> createPopulation(PopulationEntity populationEntity) {

       return null;
    }


    public List<RealEstateDTO> getRealEstateList(String district){

        List<RealEstateDTO> realEstateList = new ArrayList<RealEstateDTO>();
        List<RealEstateEntity> realEstateEntities = new ArrayList<RealEstateEntity>();
        RealEstateDTO realEstateDTO;
        RealEstateEntity realEstate;

        realEstateEntities = realEstateRepository.findAllByDongName(district);

        for(int i = 0; i < realEstateEntities.size(); i++) {
            realEstate = realEstateEntities.get(i);

            realEstateDTO = RealEstateDTO.builder()
                    .area(realEstate.getArea())
                    .buildingName(realEstate.getBuildingName())
                    .dongName(realEstate.getDongName())
                    .guName(realEstate.getGuName())
                    .houseType(realEstate.getHouseType())
                    .price(realEstate.getPrice())
                    .saleType(realEstate.getSaleType()).build();

            realEstateList.add(realEstateDTO);
        }
        return realEstateList;
    }
}
