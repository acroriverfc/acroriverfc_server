package com.acroriver.server.team.repository;


import com.acroriver.server.team.entity.Player;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class PlayerCustomRepositoryImpl extends QuerydslRepositorySupport implements PlayerCustomRepository {

    private final JPAQueryFactory queryFactory;

    public PlayerCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Player.class);
        this.queryFactory = jpaQueryFactory;
    }

}
