package com.team2.findmytown.service;

import com.team2.findmytown.config.SecurityUtil;
import com.team2.findmytown.domain.entity.*;
import com.team2.findmytown.domain.repository.*;
import com.team2.findmytown.dto.response.BookmarkDTO;
import com.team2.findmytown.dto.response.ChatHistoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MypageServiceImpl implements MypageService{
    @Autowired
    private ChatHistoryRepository chatHistoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private DistrictRepository districtRepository;

    public List<ChatHistoryDTO> importChatHistoryList(String userEmail) {

        List<ChatHistoryEntity> chatHistories;
        List<ChatHistoryDTO> chatHistoryList = new ArrayList<>();

        UserEntity user = userRepository.findByEmail(userEmail);
        ChatHistoryDTO chatHistoryDTO;

        if(userEmail.isEmpty()){
            throw new RuntimeException("Login User Info is Null");
        }else{
            chatHistories = chatHistoryRepository.findAllByUser(user);

            for(int i = 0; i < chatHistories.size(); i++){
                chatHistoryDTO = ChatHistoryDTO.builder()
                        .question(chatHistories.get(i).getQuestion())
                        .answer(chatHistories.get(i).getAnswer()).build();

                chatHistoryList.add(chatHistoryDTO);
            }
            return chatHistoryList;
        }
    }

    public Map<String, String> importMySurvey(String email){

        Map<String, String> mySurvey = new HashMap<>();
        SurveyEntity surveyEntity = surveyRepository.findAllByUserEmail(email);

        mySurvey.put("star", surveyEntity.getStar());
        mySurvey.put("review", surveyEntity.getReview());
        mySurvey.put("totalReview", surveyEntity.getTotalReview());

        return mySurvey;
    }

    public List<BookmarkDTO> importMyBookmark(){
        String user = SecurityUtil.getCurrentMemberId();

        List<BookmarkEntity> myBookmarks;
        List<BookmarkDTO> bookmarkList = new ArrayList<>();
        BookmarkDTO bookmarkDTO;

        if(user.isEmpty()){
            throw new RuntimeException("Login user Info is Null");
        }else{
            myBookmarks = bookmarkRepository.findAllByUserId(user);

            for(int i = 0; i < myBookmarks.size(); i++){
                bookmarkDTO = BookmarkDTO.builder()
                        .districtName(myBookmarks.get(i).getDistrict().getDistrictName().toString())
                        //.date(myBookmarks.get(i).getDate().toString())
                        .build();

                bookmarkList.add(bookmarkDTO);
            }
            return bookmarkList;
        }
    }

    @Transactional
    public void deleteMyBookmark(String districtName){
        String user = SecurityUtil.getCurrentMemberId();

        try {
            if(user.isEmpty()){
                throw new RuntimeException("Valid login Info");
            }else {
                DistrictEntity districtId = districtRepository.findByDistrictName(districtName);
                bookmarkRepository.deleteByUserIdAndDistrict(user, districtId);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
