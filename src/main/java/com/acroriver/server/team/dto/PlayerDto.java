package com.acroriver.server.team.dto;

import com.acroriver.server.team.entity.enums.Position;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDto {
    private Long playerId;
    private String playerName;
    private LocalDate birthDate;
    private int height;
    private int weight;
    private int appearances;
    private int goals;
    private int assists;
    private Position position;
    private int backNum;
    private String imageUrl;
    private String description;

    @Builder
    public PlayerDto(Long playerId, String playerName, LocalDate birthDate, int height, int weight, int appearances, int goals, int assists, Position position, int backNum, String imageUrl, String description) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.appearances = appearances;
        this.goals = goals;
        this.assists = assists;
        this.position = position;
        this.backNum = backNum;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
