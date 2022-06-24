package com.acroriver.server.team.entity;

import com.acroriver.server.team.entity.enums.MatchState;
import com.acroriver.server.team.entity.enums.Position;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlayMatchTest {

    @Autowired
    EntityManager em;

    // 제대로 생성되는 것을 볼 수 있다.
    @Test
    public void testPlayMatch() throws Exception {
        PlayMatch playMatch = new PlayMatch();
        Player player = makePlayer(66, 178, 70, "HwanWoo Jung");
        em.persist(playMatch);
        em.persist(player);
        playMatch.setPlayer(player);
        MatchDay matchDay = playMatch.getMatchDay();
        int assists = playMatch.getAssists();
        int goals = playMatch.getGoals();
        assertThat(player.getId()).isEqualTo(playMatch.getPlayer().getId());
        assertThat(goals).isEqualTo(0);
        assertThat(assists).isEqualTo(0);
        log.info(String.valueOf(matchDay));
        log.info(String.valueOf(player));
    }

    @Test
    public void test2() {

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
        PlayMatch playMatch2 = new PlayMatch();
        playMatch1.setPlayer(player1);
        playMatch1.setMatchDay(matchDay1);
        player1.addPlayMatch(playMatch1);
        matchDay1.addPlayMatch(playMatch1);

        log.info("PlayMatch 1 Away Name : " + playMatch1.getMatchDay().getAwayName());
        playMatch2.setPlayer(player1);
        playMatch2.setMatchDay(matchDay2);
        player1.addPlayMatch(playMatch2);
        matchDay2.addPlayMatch(playMatch2);
        em.persist(playMatch1);
        em.persist(playMatch2);
        List<PlayMatch> playMatches = player1.getPlayMatches();
        for (PlayMatch playMatch : playMatches) {
            log.info("playMatch = " + playMatch.getMatchDay().getAwayName());
        }

        assertThat(player1.getBackNum()).isEqualTo(playMatch2.getPlayer().getBackNum());

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