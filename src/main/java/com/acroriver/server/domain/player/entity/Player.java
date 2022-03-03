package com.acroriver.server.domain.player.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "player_id")
    private Long Id;

    @Column(nullable = false)
    private String playerName;

    private LocalDate birthDate;

    private int height;

    private int weight;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(nullable = false, unique = true)
    private int backNum;

    @Lob
    private String imageUrl;

    // 출장 경기, 골, 도움 수
    @Column(nullable = false)
    private int appearances;

    @Column(nullable = false)
    private int goal;

    @Column(nullable = false)
    private int assists;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String description;

    public Player(String playerName, Position position, int backNum, int appearances, int goal, int assists) {
        this.playerName = playerName;
        this.position = position;
        this.backNum = backNum;
        this.appearances = appearances;
        this.goal = goal;
        this.assists = assists;
    }

    public void changePlayerName(String playerName) {
        if (playerName == null)
            return;

        this.playerName = playerName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void changePosition(Position position) {
        if (position == null)
            return;

        this.position = position;
    }

    public void changeImageUrl(String imageUrl) {
        if (imageUrl == null)
            return;

        this.imageUrl = imageUrl;
    }


    public void changeDescription(String description) {
        if (description == null)
            return;

        this.description = description;
    }
}
