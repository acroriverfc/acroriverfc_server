package com.acroriver.server.team.dto;

import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlayMatchDto {
    private Long id;
    private Player player;
    private MatchDay matchDay;
    private int goals;
    private int assists;
}
