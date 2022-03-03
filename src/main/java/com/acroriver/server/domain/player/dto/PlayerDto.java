package com.acroriver.server.domain.player.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private String playerName;
    private LocalDate birthDate;
    private int height;


}
