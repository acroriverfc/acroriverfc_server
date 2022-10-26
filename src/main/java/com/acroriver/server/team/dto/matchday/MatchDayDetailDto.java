package com.acroriver.server.team.dto.matchday;

import com.acroriver.server.team.dto.MatchStatDto;
import com.acroriver.server.team.dto.PlayMatchDto;
import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.enums.MatchState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MatchDayDetailDto {
    private Long matchId;
    private LocalDateTime matchDate;
    private String awayName;
    private String stadium;
    private MatchState state;
    private int goals;
    private int awayGoals;
    private List<PlayMatchDto> playMatchDtoList = new ArrayList<>();
    private List<MatchStatDto> matchStatDtoList = new ArrayList<>();

    @Builder
    public MatchDayDetailDto(MatchDay matchDay) {
        this.matchId = matchDay.getId();
        this.matchDate = matchDay.getMatchDate();
        this.awayName = matchDay.getAwayName();
        this.stadium = matchDay.getStadium();
        this.state = matchDay.getState();
        this.goals = matchDay.getGoals();
        this.awayGoals = matchDay.getAwayGoals();
    }

    public void setPlayMatchDtoList(List<PlayMatchDto> playMatchDtoList) {
        this.playMatchDtoList = playMatchDtoList;
    }

    public void setMatchStatDtoList(List<MatchStatDto> matchStatDtoList) {
        this.matchStatDtoList = matchStatDtoList;
    }
}
