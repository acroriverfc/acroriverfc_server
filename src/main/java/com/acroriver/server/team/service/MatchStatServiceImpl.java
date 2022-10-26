package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.MatchStatDto;
import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.MatchStat;
import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.repository.MatchDayRepository;
import com.acroriver.server.team.repository.MatchStatRepository;
import com.acroriver.server.team.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchStatServiceImpl implements MatchStatService {

    private final MatchDayRepository matchDayRepository;
    private final MatchStatRepository matchStatRepository;
    private final PlayerRepository playerRepository;

    // 어시가 있을 경우
    @Transactional
    @Override
    public MatchStatDto createMatchStat(Long goalId, Long assistId, Long matchId) {
        MatchDay matchDay = matchDayRepository.findById(matchId).orElseThrow(EntityNotFoundException::new);
        Optional<Player> optionalPlayer = playerRepository.findById(assistId);

        // 만약 어시가 없다면
        if (optionalPlayer.isEmpty())
            return createMatchStatWithoutAssist(goalId, matchId);


        Player goalPlayer = playerRepository.findById(goalId).orElseThrow(EntityNotFoundException::new);
        Player assistPlayer = optionalPlayer.get();
        MatchStat newMatchStat = MatchStat.builder()
                .matchDay(matchDay)
                .goal_player(goalPlayer)
                .assist_player(assistPlayer)
                .build();

        MatchStat save = matchStatRepository.save(newMatchStat);
        // 1골 추가 및 경기에 기록 추가
        matchDay.addMatchStats(save);
        matchDay.updateGoals();
        goalPlayer.updateGoals();
        assistPlayer.updateAssists();

        return MatchStatDto.builder()
                .goalPlayerName(goalPlayer.getPlayerName())
                .assistPlayerName(assistPlayer.getPlayerName())
                .build();
    }

    // 어시가 없을 경우
    @Transactional
    @Override
    public MatchStatDto createMatchStatWithoutAssist(Long goalId, Long matchId) {
        MatchDay matchDay = matchDayRepository.findById(matchId).orElseThrow(EntityNotFoundException::new);
        Player goalPlayer = playerRepository.findById(goalId).orElseThrow(EntityNotFoundException::new);
        MatchStat newMatchStat = MatchStat.builder()
                .matchDay(matchDay)
                .goal_player(goalPlayer)
                .build();

        MatchStat save = matchStatRepository.save(newMatchStat);

        matchDay.updateGoals();
        matchDay.addMatchStats(save);
        goalPlayer.updateGoals();

        return MatchStatDto.builder()
                .goalPlayerName(goalPlayer.getPlayerName())
                .build();
    }
}
