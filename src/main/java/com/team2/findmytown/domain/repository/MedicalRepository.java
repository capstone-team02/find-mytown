package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.MedicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRepository extends JpaRepository<MedicalEntity, Long> {

    MedicalEntity findByHospital(int hospital);

}
