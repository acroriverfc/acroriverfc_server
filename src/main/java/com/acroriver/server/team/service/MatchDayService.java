package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.MatchDayDto;
import com.acroriver.server.team.entity.enums.MatchState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface MatchDayService {
    public List<MatchDayDto> findAll();

    public MatchDayDto findOne(Long matchId);

    public List<MatchDayDto> findByMonth(int month);

    @Transactional
    public void updateMatchResult(Long matchId, MatchState matchState);
}
