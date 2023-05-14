package com.team2.findmytown.domain.repository;



import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
   // @Query("select l from DistrictEntity l where l.districtName=?1")
    DistrictEntity findByDistrictName(String districtName);

    DistrictEntity findDistinctByGuEntityAndDistrictName(GuEntity guEntity, String districtName);

    List<DistrictEntity> findAllByDistrictName(String districtName);

}
