package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.MatchDayDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchDayService {

    // 경기 생성
    public void createMatchDay(MatchDayDto matchDayDto);

    public List<MatchDayDto> findAll();

    public MatchDayDto findOne(Long matchId);

    public List<MatchDayDto> findByState(String state);

    // 월 별 경기 일정
    public List<MatchDayDto> findByDate(int year, int month);

    public void updateMatchInfo(MatchDayDto matchDayDto);

}
