package com.team2.findmytown.domain.repository;

import com.team2.findmytown.domain.entity.ChatHistoryEntity;
import com.team2.findmytown.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatHistoryRepository extends JpaRepository <ChatHistoryEntity, String> {
        List<ChatHistoryEntity> findAllByUser(UserEntity user);
}
