package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.MatchDayDto;
import com.acroriver.server.team.entity.enums.MatchState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateMatchResult(Long matchId, MatchState matchState);

    // 출전 선수 등록하기
    @Transactional
    public void createPlayMatch(Long matchId, Long playerId);

}
