package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.MatchStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchStatRepository extends JpaRepository<MatchStat, Long> {
}
