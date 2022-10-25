package com.acroriver.server.team.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MatchStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ms_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private MatchDay matchDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "player_id", name = "goal_id", nullable = false)
    private Player goal_player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "player_id", name = "assist_id")
    private Player assist_player;

    @Builder
    public MatchStat(MatchDay matchDay, Player goal_player, Player assist_player) {
        this.matchDay = matchDay;
        this.goal_player = goal_player;
        this.assist_player = assist_player;
    }

    public void setMatchDay(MatchDay matchDay) {
        this.matchDay = matchDay;
    }
}
