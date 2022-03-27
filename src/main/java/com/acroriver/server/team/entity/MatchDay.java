package com.acroriver.server.team.entity;

import com.acroriver.server.team.entity.enums.MatchState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @GeneratedValue
    @Column(name = "match_id")
    private Long id;

    private LocalDateTime matchDate;

    private String awayName;

    @Enumerated(EnumType.STRING)
    private MatchState state;

    @OneToMany(mappedBy = "matchDay")
    private List<PlayMatch> playMatches = new ArrayList<>();

    public void changeAwayName(String awayName) {
        this.awayName = awayName;
    }

    public void changeMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public void changeMatchState(MatchState state) {
        this.state = state;
    }

    // 연관관계 메서드
    public void addPlayMatch(PlayMatch playMatch) {
        this.playMatches.add(playMatch);
        playMatch.setMatchDay(this);
    }
}
