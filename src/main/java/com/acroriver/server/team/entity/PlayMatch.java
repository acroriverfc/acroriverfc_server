package com.acroriver.server.team.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@IdClass(PlayMatchId.class)
public class PlayMatch {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchDay matchDay;

    @Column(name = "goals")
    @ColumnDefault("0")
    private int goals;

    @Column(name = "assists")
    @ColumnDefault("0")
    private int assists;


    @Builder
    public PlayMatch(Player player, MatchDay matchDay) {
        this.player = player;
        this.matchDay = matchDay;
        this.goals = 0;
        this.assists = 0;
    }

    public void updateGoals(int goals) {
        this.goals = goals;
    }

    public void updateAssists(int assists) {
        this.assists = assists;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMatchDay(MatchDay matchDay) {
        this.matchDay = matchDay;
    }
}
