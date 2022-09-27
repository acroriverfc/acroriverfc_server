package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.enums.MatchState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchDayRepository extends JpaRepository<MatchDay, Long>, MatchDayCustomRepository {
    public List<MatchDay> findByState(MatchState state);
}
