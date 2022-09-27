package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.MatchDay;

import java.util.List;

public interface MatchDayCustomRepository {
    public List<MatchDay> findByMonth(int month);
}
