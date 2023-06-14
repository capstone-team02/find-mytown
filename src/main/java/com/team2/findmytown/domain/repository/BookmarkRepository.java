package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.BookmarkEntity;
import com.team2.findmytown.domain.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, String> {
    List<BookmarkEntity> findAllByUserId(String id);

    void deleteByUserIdAndDistrict(String id, DistrictEntity district);
}