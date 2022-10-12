package com.acroriver.server.team.repository;


import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.repository.comparator.PlayerComparator;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.acroriver.server.team.entity.QPlayer.player;

@Slf4j
@Repository
public class PlayerCustomRepositoryImpl extends QuerydslRepositorySupport implements PlayerCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final PlayerComparator comparator = new PlayerComparator();

    public PlayerCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Player.class);
        this.queryFactory = jpaQueryFactory;
    }

    @Override
    public List<Player> findAll() {
        return queryFactory.selectFrom(player)
                .orderBy(player.backNum.asc())
                .fetch();
    }

    @Override
    public List<List<Player>> findRank() {
        List<List<Player>> ret = new ArrayList<>();

        List<Player> fetch = queryFactory.selectFrom(player)
                .orderBy(player.backNum.asc())
                .fetch();

        // 골 순 정렬
        List<Player> rankByGoals = fetch.stream()
                .sorted(comparator.compareByGoals)
                .collect(Collectors.toList());

        // 어시 순 정렬
        List<Player> rankByAssists = fetch.stream()
                .sorted(comparator.compareByAssists)
                .collect(Collectors.toList());

        // 출장 수 순 정렬
        List<Player> rankByAppearances = fetch.stream()
                .sorted(comparator.compareByAppearances)
                .collect(Collectors.toList());

        ret.add(rankByGoals);
        ret.add(rankByAssists);
        ret.add(rankByAppearances);
        return ret;
    }
}
