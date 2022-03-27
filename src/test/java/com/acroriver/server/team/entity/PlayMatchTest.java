package com.acroriver.server.team.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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
        em.persist(playMatch);
        MatchDay matchDay = playMatch.getMatchDay();
        Player player = playMatch.getPlayer();
        int assists = playMatch.getAssists();
        int goals = playMatch.getGoals();
        Long id = playMatch.getId();
        assertThat(id).isEqualTo(1L);
        assertThat(goals).isEqualTo(0);
        assertThat(assists).isEqualTo(0);
        log.info(String.valueOf(matchDay));
        log.info(String.valueOf(player));
    }
}