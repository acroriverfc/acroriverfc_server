package com.acroriver.server.domain.team.dto;

import com.acroriver.server.domain.team.entity.PlayMatch;
import com.acroriver.server.domain.team.entity.enums.MatchState;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MatchDayDto {
    private Long id;
    private LocalDate matchDate;
    private String awayName;
    private MatchState result;
    private List<PlayMatch> playMatchList = new ArrayList<>();

    public MatchDayDto(Long id, LocalDate matchDate, String awayName, MatchState result) {
        this.id = id;
        this.matchDate = matchDate;
        this.awayName = awayName;
        this.result = result;
    }
}
