package com.acroriver.server.team.entity;

import com.acroriver.server.team.entity.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "player_id")
    private Long id;

    private String playerName;

    private LocalDate birthDate;

    private int height;

    private int weight;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(unique = true)
    private int backNum;

    @Lob
    private String imageUrl;

    // 출장 경기, 골, 도움 수
    @Column(nullable = false)
    @ColumnDefault("0")
    private int appearances;

    @Column(name = "goals", nullable = false)
    @ColumnDefault("0")
    private int goals;

    @Column(name = "assists", nullable = false)
    @ColumnDefault("0")
    private int assists;

    @Lob
    private String description;

    @OneToMany(mappedBy = "player")
    private List<PlayMatch> playMatches = new ArrayList<>();

    // == 비즈니스 로직 == //
    public void changePlayerName(String playerName) {
        if (playerName == null)
            return;

        this.playerName = playerName;
    }

    public void changeBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void changeBackNum(int backNum) {
        this.backNum = backNum;
    }

    public void changeHeight(int height) {
        this.height = height;
    }

    public void changeWeight(int weight) {
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

    public void changeMatchInfo(int appearances, int goals, int assists) {
        this.appearances = appearances;
        this.goals = goals;
        this.assists = assists;
    }

    // == 연관관계 메서드 == //
    public void addPlayMatch(PlayMatch playMatch) {
        playMatches.add(playMatch);
        playMatch.setPlayer(this);
    }

    // 출전한 경기 수 증가. 골이랑 어시 수 넘겨서 증가 시키기
    public void updateMatchInfo(int goals, int assists) {
        this.appearances += 1;
        this.goals += goals;
        this.assists += assists;
    }

    // 만약 존재하는 경기를 잘못 기록해서 삭제할 경우 감소.
    public void deleteMatchInfo(int goals, int assists) {
        this.appearances -= 1;
        this.goals -= goals;
        this.assists -= assists;
    }

}
