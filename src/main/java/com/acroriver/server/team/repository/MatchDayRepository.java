package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.MatchDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchDayRepository extends JpaRepository<MatchDay, Long>, MatchDayCustomRepository {
}
