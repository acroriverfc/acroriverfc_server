package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.MatchDayDto;
import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.enums.MatchState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface MatchDayService {

    public void save(MatchDay matchDay);

    public List<MatchDayDto> findAll();

    public MatchDayDto findOne(Long matchId);

    public List<MatchDayDto> findByMonth(int month);

    @Transactional
    public void updateMatchResult(Long matchId, MatchState matchState);

    // 출전 선수 등록하기
    @Transactional
    public void createPlayMatch(Long matchId, Long playerId);

    // 기록 업데이트 하기
    @Transactional
    public void updateMatchDayPlayer(Long matchId, Long playerId, int goals, int assists);
}
