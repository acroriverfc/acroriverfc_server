package com.acroriver.server.team.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlayMatchDto {
    // 경기 입장에서만 선수를 조회하는 경우
    private String playerName;
    private int backNum;

    @Builder
    public PlayMatchDto(String playerName, int backNum) {
        this.playerName = playerName;
        this.backNum = backNum;
    }
}
