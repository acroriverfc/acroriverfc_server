package com.acroriver.server;

import com.acroriver.server.domain.team.entity.MatchDay;
import com.acroriver.server.domain.team.entity.enums.MatchState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class InitDB {

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private EntityManager em;

        public void dbInit1() {
            MatchDay matchDay1 = new MatchDay(1L, LocalDateTime.now(), "away1", MatchState.BEFORE, new ArrayList<>());
            MatchDay matchDay2 = new MatchDay(2L, LocalDateTime.now(), "away2", MatchState.DRAW, new ArrayList<>());


/*
Match Day
    @Id
    @GeneratedValue
    @Column(name = "match_id")
    private Long Id;

    @Column(nullable = false)
    private LocalDateTime matchDate;

    private String awayName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchState state;

    @OneToMany(mappedBy = "matchDay")
    private List<PlayMatch> playMatches = new ArrayList<>();
 */
        }
    }
}
