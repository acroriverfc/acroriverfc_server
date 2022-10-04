package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.PlayMatch;

import java.util.List;

public interface PlayMatchCustomRepository {
    public PlayMatch findByTwoIds(Long playerId, Long matchId);

    public List<PlayMatch> findByPlayerId(Long playerId);
}
