package com.acroriver.server.team.repository;


import com.acroriver.server.team.entity.PlayMatch;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.acroriver.server.team.entity.QPlayMatch.playMatch;

@Repository
public class PlayMatchCustomRepositoryImpl extends QuerydslRepositorySupport implements PlayMatchCustomRepository {

    private final JPAQueryFactory queryFactory;

    public PlayMatchCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(PlayMatch.class);
        this.queryFactory = jpaQueryFactory;
    }

    @Override
    public PlayMatch findByTwoIds(Long playerId, Long matchId) {
        // 어차피 유니크 키라 하나밖에 안나오므로, FetchFirst 를 사용해 주었다.
        return queryFactory.selectFrom(playMatch)
                .where(playMatch.player.id.eq(playerId))
                .where(playMatch.matchDay.id.eq(matchId)).fetchFirst();
    }
}
