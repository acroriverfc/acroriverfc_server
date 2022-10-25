package com.acroriver.server.team.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MatchStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ms_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    @Column(nullable = false, name = "match_id")
    private MatchDay matchDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @Column(nullable = false, name = "goal_id")
    private Player goal_player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @Column(name = "assist_id")
    private Player assist_player;

    @Builder
    public MatchStats(MatchDay matchDay, Player goal_player, Player assist_player) {
        this.matchDay = matchDay;
        this.goal_player = goal_player;
        if (assist_player != null)
            this.assist_player = assist_player;
    }
}
