package com.acroriver.server.team.dto;

import com.acroriver.server.team.entity.PlayMatch;
import com.acroriver.server.team.entity.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long Id;
    private String playerName;
    private LocalDate birthDate;
    private int height;
    private int weight;
    private Position position;
    private int backNum;
    private String imageUrl;
    // 출장 경기, 골, 도움 수
    private int appearances;
    private int goal;
    private int assists;
    private String description;
    private List<PlayMatch> playMatchList;

}
