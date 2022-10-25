package com.acroriver.server.team.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MatchStatDto {
    private Long msId;
    private String goalPlayerName;
    private String assistPlayerName;

    @Builder
    public MatchStatDto(Long msId, String goalPlayerName, String assistPlayerName) {
        this.msId = msId;
        this.goalPlayerName = goalPlayerName;
        this.assistPlayerName = assistPlayerName;
    }

    @Builder
    public MatchStatDto(Long msId, String goalPlayerName) {
        this.msId = msId;
        this.goalPlayerName = goalPlayerName;
    }
}
