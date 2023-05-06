package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import com.team2.findmytown.domain.entity.PopulationEntity;

import java.util.List;

public interface DataService {
    public GuEntity findOrCreateNew(GuEntity guEntity);
    public List<DistrictEntity> createDistrict(final DistrictEntity districtEntity);

    public List<PopulationEntity>createPopulation(final PopulationEntity populationEntity);

}
