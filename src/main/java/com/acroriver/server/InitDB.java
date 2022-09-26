package com.acroriver.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDB {

    /*
    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Player player1 = makePlayer(66, 178, 70, "HwanWoo Jung");
            Player player2 = makePlayer(9, 185, 67, "Messi");
            em.persist(player1);
            em.persist(player2);

            MatchDay matchDay1 = new MatchDay();
            MatchDay matchDay2 = new MatchDay();
            matchDay1.changeMatchDate(LocalDateTime.now());
            matchDay1.changeAwayName("FC Mellow");
            matchDay2.changeMatchDate(LocalDateTime.now());
            matchDay2.changeAwayName("FC Slat");
            matchDay2.changeMatchState(MatchState.WIN);
            em.persist(matchDay1);
            em.persist(matchDay2);

            PlayMatch playMatch1 = new PlayMatch();
            playMatch1.setPlayer(player1);
            playMatch1.setMatchDay(matchDay1);
            player1.addPlayMatch(playMatch1);
            matchDay1.addPlayMatch(playMatch1);
            em.persist(playMatch1);
            List<PlayMatch> playMatches = player1.getPlayMatches();
            for (PlayMatch playMatch : playMatches) {
                System.out.println("playMatch = " + playMatch);
            }
        }

        private Player makePlayer(int backNum, int height, int weight, String playerName) {
            Player player = new Player();
            player.changeBackNum(backNum);
            player.changeHeight(height);
            player.changeWeight(weight);
            player.changePosition(Position.DF);
            player.changePlayerName(playerName);
            player.changeDescription("Normal Player in Acroriver FC");
            return player;
        }
    }
     */
}
