package com.acroriver.server.team.dto;

import com.acroriver.server.team.entity.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
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
}
