package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.Player;

import java.util.List;

public interface PlayerCustomRepository {
    public List<Player> findAll();

    public List<List<Player>> findRank();
}
