package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.enums.MatchState;

import java.util.List;

public interface MatchDayCustomRepository {
    public List<MatchDay> findByMonth(int month);

    public List<MatchDay> findByState(MatchState state);

    public List<MatchDay> findByDate(int year, int month);
}
