package com.acroriver.server.domain.team.entity;

import com.acroriver.server.domain.team.entity.enums.MatchState;
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
    private Long Id;

    @Column(nullable = false)
    private LocalDateTime matchDate;

    private String awayName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchState state;

    @OneToMany(mappedBy = "matchDay")
    private List<PlayMatch> playMatches = new ArrayList<>();


}
