package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.PlayMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayMatchRepository extends JpaRepository<PlayMatch, Long>, PlayMatchCustomRepository {
}
