package com.acroriver.server.team.dto;

import com.acroriver.server.team.entity.enums.MatchState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDayDto {
    private Long matchId;
    private LocalDateTime matchDate;
    private String awayName;
    private String stadium;
    private MatchState state;
    private int goals;
    private int awayGoals;
}
