package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.MatchStatDto;

public interface MatchStatService {
    public MatchStatDto createMatchStat(Long goalId, Long assistId, Long matchId);

    public MatchStatDto createMatchStatWithoutAssist(Long goalId, Long matchId);
}
