package com.acroriver.server.domain.team.repository;

import com.acroriver.server.domain.team.entity.Player;
import com.acroriver.server.domain.team.entity.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlayerRepository extends JpaRepository<Player, Long> {
    public Player findByPlayerName(String playerName);

    public Player findByBackNum(int backNum);

    public List<Player> findPlayersByPosition(Position position);
}
