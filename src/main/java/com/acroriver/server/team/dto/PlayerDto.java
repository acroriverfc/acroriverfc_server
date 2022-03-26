package com.acroriver.server.domain.team.dto;

import com.acroriver.server.domain.team.entity.PlayMatch;
import com.acroriver.server.domain.team.entity.enums.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
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
    private List<PlayMatch> playMatchList = new ArrayList<>();

    public PlayerDto(Long id, String playerName, LocalDate birthDate, int height, int weight, Position position, int backNum, String imageUrl, int appearances, int goal, int assists, String description) {
        Id = id;
        this.playerName = playerName;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.backNum = backNum;
        this.imageUrl = imageUrl;
        this.appearances = appearances;
        this.goal = goal;
        this.assists = assists;
        this.description = description;
    }
}
