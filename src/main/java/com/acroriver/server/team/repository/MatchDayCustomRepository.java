package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.enums.MatchState;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchDayCustomRepository {
    public List<MatchDay> findByMonth(int month);

    public List<MatchDay> findByState(MatchState state);

    public List<MatchDay> findByDate(int year, int month);
}
