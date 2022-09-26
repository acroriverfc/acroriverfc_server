package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.MatchDay;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MatchDayCustomRepositoryImpl extends QuerydslRepositorySupport implements MatchDayCustomRepository {

    private final JPAQueryFactory queryFactory;

    public MatchDayCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(MatchDay.class);
        this.queryFactory = jpaQueryFactory;
    }

    @Override
    public List<MatchDay> findByMonth(int month) {
        return null;
    }
}
