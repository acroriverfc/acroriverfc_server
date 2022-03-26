package com.acroriver.server.domain.team.repository;

import com.acroriver.server.domain.team.entity.MatchDay;
import com.acroriver.server.domain.team.entity.PlayMatch;
import com.acroriver.server.domain.team.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayMatchRepository extends JpaRepository<PlayMatch, Long> {
    public PlayMatch findPlayMatchByPlayer(Player player);

    public PlayMatch findPlayMatchByMatchDay(MatchDay matchDay);
}
