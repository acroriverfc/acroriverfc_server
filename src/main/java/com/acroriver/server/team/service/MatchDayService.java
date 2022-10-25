package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.matchday.MatchDayDetailDto;
import com.acroriver.server.team.dto.matchday.MatchDayDto;

import java.util.List;

public interface MatchDayService {

    // 경기 생성
    public MatchDayDto createMatchDay(MatchDayDto matchDayDto);

    public List<MatchDayDto> findAll();

    public MatchDayDto findOne(Long matchId);

    public List<MatchDayDto> findByState(String state);

    // 월 별 경기 일정
    public List<MatchDayDto> findByDate(int year, int month);

    public MatchDayDto updateMatchInfo(MatchDayDto matchDayDto);

    // 다음 경기 (메인 화면에 표시될 것)
    public MatchDayDto findNextMatch();

    public MatchDayDetailDto findMatchDetail(Long matchId);

}
