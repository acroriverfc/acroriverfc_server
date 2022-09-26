package com.acroriver.server.team.service;

import org.springframework.stereotype.Service;

@Service
public interface PlayMatchService {
    public void addPlayerToMatch(Long playerId, Long matchId);

    public void updatePlayerStats(Long playerId, Long matchId, int goal, int assists);
}
