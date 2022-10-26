package com.acroriver.server.team.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MatchStatRequestDto {
    private Long assistId;
    private Long goalId;
}
