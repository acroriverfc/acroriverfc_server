package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.PlayMatch;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayMatchCustomRepository {
    public PlayMatch findByTwoIds(Long playerId, Long matchId);
}
