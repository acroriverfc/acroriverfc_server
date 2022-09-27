package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.PlayMatchDto;
import org.springframework.stereotype.Service;

@Service
public interface PlayMatchService {
    public void addPlayerToMatch(Long playerId, Long matchId);

    public PlayMatchDto findPlayMatchById(Long playMatchId);

    public void updatePlayerStats(Long playerId, Long matchId, int goal, int assists);

    public PlayMatchDto findPlayMatchByTwoIds(Long playerId, Long matchId);
}
