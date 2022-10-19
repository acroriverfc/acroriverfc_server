package com.acroriver.server.team.repository;

import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.enums.MatchState;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

import static com.acroriver.server.team.entity.QMatchDay.matchDay;

@Slf4j
public class MatchDayCustomRepositoryImpl extends QuerydslRepositorySupport implements MatchDayCustomRepository {

    private final JPAQueryFactory queryFactory;

    public MatchDayCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(MatchDay.class);
        this.queryFactory = jpaQueryFactory;
    }

    @Override
    public List<MatchDay> findByState(MatchState state) {
        return queryFactory.selectFrom(matchDay)
                .where(matchDay.state.eq(state))
                .orderBy(matchDay.matchDate.asc())
                .fetch();
    }

    @Override
    public List<MatchDay> findByDate(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime startTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));
        LocalDateTime endTime = LocalDateTime.of(yearMonth.atEndOfMonth(), LocalTime.of(23, 59, 59));

        return queryFactory.selectFrom(matchDay)
                .where(matchDay.matchDate.between(startTime, endTime))
                .orderBy(matchDay.matchDate.asc())
                .fetch();
    }

    @Override
    public MatchDay findNextMatch() {
        LocalDateTime now = LocalDateTime.now();
        return queryFactory.selectFrom(matchDay)
                .where(matchDay.matchDate.after(now))
                .orderBy(matchDay.matchDate.asc())
                .fetchFirst();
    }
}
