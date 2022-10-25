package com.acroriver.server.team.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "constraintName",
                        columnNames = {"player_id", "match_id"}
                )
        }
)
@Getter
@NoArgsConstructor
public class PlayMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchDay matchDay;

    @Builder
    public PlayMatch(Player player, MatchDay matchDay) {
        this.player = player;
        this.matchDay = matchDay;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMatchDay(MatchDay matchDay) {
        this.matchDay = matchDay;
    }
}