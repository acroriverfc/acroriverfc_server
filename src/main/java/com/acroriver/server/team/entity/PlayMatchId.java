package com.acroriver.server.team.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayMatchId implements Serializable {
    private Long player;
    private Long matchDay;
}
