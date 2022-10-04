package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.entity.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerCustomRepository {

    public Player findByBackNum(int backNum);

    public List<Player> findByPositionOrderByBackNum(Position position);

    public List<Player> findAll();
}
