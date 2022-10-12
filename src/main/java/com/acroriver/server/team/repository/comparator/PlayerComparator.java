package com.acroriver.server.team.repository.comparator;

import com.acroriver.server.team.entity.Player;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class PlayerComparator {
    public Comparator<Player> compareByGoals = Comparator.comparing(Player::getGoals)
            .thenComparing(Player::getAppearances)
            .reversed()
            .thenComparing(Player::getBackNum);

    public Comparator<Player> compareByAppearances = Comparator.comparing(Player::getAppearances)
            .reversed()
            .thenComparing(Player::getBackNum);

    public Comparator<Player> compareByAssists = Comparator.comparing(Player::getAssists)
            .thenComparing(Player::getAppearances)
            .reversed()
            .thenComparing(Player::getBackNum);
}
