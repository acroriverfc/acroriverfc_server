package com.acroriver.server.team.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayMatchDto {
    private Long playerId;
    private Long matchId;
    private int goals;
    private int assists;
}
