package com.acroriver.server.team.dto.matchday;

import com.acroriver.server.team.entity.enums.MatchState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MatchDayDto {
    private Long matchId;
    private LocalDateTime matchDate;
    private String awayName;
    private String stadium;
    private MatchState state;
    private int goals;
    private int awayGoals;

    @Builder
    public MatchDayDto(Long matchId, LocalDateTime matchDate, String awayName, String stadium, MatchState state, int goals, int awayGoals) {
        this.matchId = matchId;
        this.matchDate = matchDate;
        this.awayName = awayName;
        this.stadium = stadium;
        this.state = state;
        this.goals = goals;
        this.awayGoals = awayGoals;
    }
}
