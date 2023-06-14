package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.GuAndDistrictEntity;
import com.team2.findmytown.domain.entity.RealEstateEntity;
import com.team2.findmytown.domain.repository.GuAndDistrictRepository;
import com.team2.findmytown.domain.repository.RealEstateRepository;
import lombok.extern.slf4j.Slf4j;
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
public class RealEstateService {
    private final RealEstateRepository realEstateRepository;
    private final GuAndDistrictRepository guAndDistrictRepository;

    public RealEstateService(RealEstateRepository realEstateRepository, GuAndDistrictRepository guAndDistrictRepository) {
        this.realEstateRepository = realEstateRepository;
        this.guAndDistrictRepository = guAndDistrictRepository;
    }


    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue == null) return null;
        return nValue.getNodeValue();
    }




    public void trade() {
        try {
            List<RealEstateEntity> realEstateList = new ArrayList<>();
            List<String> buildingName = new ArrayList<>();
            for (int start = 0, end = 999; start < 13000; start += 1000, end += 1000) {
                String url = "http://openapi.seoul.go.kr:8088/4b614a6876776c6434326f6544426b/xml/tbLnOpendataRtmsV/" + String.valueOf(start) + "/" + String.valueOf(end) + "/";

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
                            RealEstateEntity realEstate = RealEstateEntity.builder()
                                    .buildingName(getTagValue("BLDG_NM", element))
                                    .guName(getTagValue("SGG_NM", element))
                                    .dongName(getTagValue("BJDONG_NM", element))
                                    .price(getTagValue("OBJ_AMT", element))
                                    .area(getTagValue("BLDG_AREA", element))
                                    .houseType(getTagValue("HOUSE_TYPE", element))
                                    .saleType("매매")
                                    .build();
                            buildingName.add(getTagValue("BLDG_NM", element));
                            if (realEstateRepository.findByBuildingName(getTagValue("BLDG_NM", element)).isEmpty()) {
                                realEstateList.add(realEstate);
                            }
                        }
                    }
                }
            }
            realEstateRepository.saveAll(realEstateList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
