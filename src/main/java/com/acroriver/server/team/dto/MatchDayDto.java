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
    private LocalDateTime matchDate;
    private String awayName;
    private MatchState state;
}
