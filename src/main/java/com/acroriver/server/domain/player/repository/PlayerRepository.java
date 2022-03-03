package com.acroriver.server.domain.player.repository;

import com.acroriver.server.domain.player.entity.Player;

import java.util.List;

public interface PlayerRepository {
    public Player findByName(String name);

    public Player findByBackNum(int backNum);

    public List<Player> findAllPlayer();
}
