package com.acroriver.server.team.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PlayMatch {

    @Id
    @GeneratedValue
    @Column(name = "play_match_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchDay matchDay;

    @Column(name = "goals")
    @ColumnDefault("0")
    private int goals;

    @Column(name = "assists")
    @ColumnDefault("0")
    private int assists;

    public PlayMatch(Player player, MatchDay matchDay) {
        this.player = player;
        this.matchDay = matchDay;
    }

    public PlayMatch(Player player, MatchDay matchDay, int goals, int assists) {
        this.player = player;
        this.matchDay = matchDay;
        this.goals = goals;
        this.assists = assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
