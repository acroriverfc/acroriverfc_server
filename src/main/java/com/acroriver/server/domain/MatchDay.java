package com.acroriver.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

}
