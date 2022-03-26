package com.acroriver.server.team.dto;

import com.acroriver.server.team.entity.PlayMatch;
import com.acroriver.server.team.entity.enums.MatchState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDayDto {
    private Long id;
    private LocalDate matchDate;
    private String awayName;
    private MatchState result;
    private List<PlayMatch> playMatchList;
}
