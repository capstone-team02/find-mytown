package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.*;

import java.util.List;

public interface DataService {
    public GuEntity findOrCreateNew(GuEntity guEntity);
    public List<DistrictEntity> createDistrict(final DistrictEntity districtEntity);

    public DistrictEntity findDistractEntity(final GuEntity guEntity,final String dongName);
    public DistrictEntity updateFacilityDistractEntity(final DistrictEntity districtEntity, final FacilityEntity facilityEntity);

    public List<PopulationEntity>createPopulation(final PopulationEntity populationEntity);

}
