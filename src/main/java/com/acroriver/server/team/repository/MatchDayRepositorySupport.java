package com.acroriver.server.team.repository;

import com.acroriver.server.team.dto.MatchDayDto;
import com.acroriver.server.team.entity.MatchDay;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MatchDayRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory; // For Querydsl

    public MatchDayRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(MatchDayDto.class);
        this.queryFactory = jpaQueryFactory;
    }


    public List<MatchDay> findByMonth(int month) {
        //queryFactory.query()
        return null;
    }
}
