package com.acroriver.server.team.entity;

import com.acroriver.server.team.entity.enums.MatchState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MatchDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private LocalDateTime matchDate;

    private String awayName;

    // 경기장 이름
    private String stadium;

    @Enumerated(EnumType.STRING)
    private MatchState state;

    // 우리 팀 득점 수
    @ColumnDefault("0")
    private int goals;

    // 상대 팀 득점 수
    @ColumnDefault("0")
    private int awayGoals;

    // 다 대 다 관계를 분산
    @OneToMany(mappedBy = "matchDay")
    private List<PlayMatch> playMatches = new ArrayList<>();

    // 경기 생성
    @Builder
    public MatchDay(LocalDateTime matchDate, String awayName, MatchState state) {
        this.matchDate = matchDate;
        this.awayName = awayName;
        this.state = state;
    }

    public void changeMatchAwayName(String awayName) {
        if (awayName == null)
            return;
        this.awayName = awayName;
    }

    public void changeMatchDate(LocalDateTime matchDate) {
        if (matchDate == null)
            return;
        this.matchDate = matchDate;
    }

    public void changeStadium(String stadium) {
        if (stadium == null)
            return;
        this.stadium = stadium;
    }

    public void changeGoals(int goals) {
        this.goals = goals;
    }

    public void changeAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public void changeMatchState(MatchState state) {
        if (state == null)
            return;
        this.state = state;
    }

    // 연관관계 메서드
    public void addPlayMatch(PlayMatch playMatch) {
        this.playMatches.add(playMatch);
        playMatch.setMatchDay(this);
    }
}
