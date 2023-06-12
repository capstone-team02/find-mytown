package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.SurveyEntity;
import com.team2.findmytown.dto.request.SurveyDTO;
import com.team2.findmytown.dto.response.ChatHistoryDTO;

import java.util.List;
import java.util.Map;

public interface MypageService {
    public List<ChatHistoryDTO> importChatHistoryList(String userEmail);

    public Map<String, String> importMySurvey(String userEmail);
}
