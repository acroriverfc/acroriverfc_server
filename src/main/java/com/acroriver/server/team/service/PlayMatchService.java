package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.PlayMatchDto;

public interface PlayMatchService {
    public void addPlayerToMatch(Long playerId, Long matchId);

    public PlayMatchDto findPlayMatchById(Long playMatchId);

    public void updatePlayMatchStats(Long playerId, Long matchId, int goal, int assists);

    public PlayMatchDto findPlayMatchByTwoIds(Long playerId, Long matchId);
}