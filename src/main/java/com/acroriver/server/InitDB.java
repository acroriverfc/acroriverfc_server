package com.acroriver.server;

import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.PlayMatch;
import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.entity.enums.MatchState;
import com.acroriver.server.team.entity.enums.Position;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;


@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;


    public void init() {
        initService.dbInit1();
    }


    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            MatchDay matchDay1 = new MatchDay(1L, LocalDateTime.now(), "away1", MatchState.BEFORE, new ArrayList<>());
            em.persist(matchDay1);

            Player player = new Player("Hwan Woo", Position.DF, 66, 178, 67);
            em.persist(player);

            PlayMatch playMatch1 = new PlayMatch(player, matchDay1);
            matchDay1.addPlayMatch(playMatch1);

            MatchDay matchDay2 = new MatchDay(2L, LocalDateTime.now(), "away2", MatchState.DRAW, new ArrayList<>());
            em.persist(matchDay2);
            PlayMatch playMatch2 = new PlayMatch(player, matchDay2);
            playMatch2.setGoals(1);
            playMatch2.setAssists(1);

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
