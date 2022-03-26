package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.PlayMatch;
import com.acroriver.server.team.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayMatchRepository extends JpaRepository<PlayMatch, Long> {
    public PlayMatch findPlayMatchByPlayer(Player player);

    public PlayMatch findPlayMatchByMatchDay(MatchDay matchDay);
}
