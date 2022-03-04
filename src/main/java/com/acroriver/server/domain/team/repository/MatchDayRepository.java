package com.acroriver.server.domain.team.repository;

import com.acroriver.server.domain.team.entity.MatchDay;
import com.acroriver.server.domain.team.entity.enums.MatchState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchDayRepository extends JpaRepository<MatchDay, Long> {

    public MatchDay findMatchDayByAwayName(String awayName);

    public List<MatchDay> findMatchDayListByState(MatchState state);

    public List<MatchDay> findAll();
}
