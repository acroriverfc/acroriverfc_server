package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.PlayMatchDto;
import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.PlayMatch;
import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.repository.MatchDayRepository;
import com.acroriver.server.team.repository.PlayMatchRepository;
import com.acroriver.server.team.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayMatchServiceImpl implements PlayMatchService {

    private final PlayMatchRepository playMatchRepository;
    private final PlayerRepository playerRepository;
    private final MatchDayRepository matchDayRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void addPlayerToMatch(Long playerId, Long matchId) {
        Player player = playerRepository.findById(playerId).get();
        MatchDay matchDay = matchDayRepository.findById(matchId).get();
        PlayMatch playMatch = PlayMatch.builder()
                .player(player)
                .matchDay(matchDay)
                .build();
        PlayMatch save = playMatchRepository.save(playMatch);

        matchDay.addPlayMatch(save);
        player.addPlayMatch(save);
        matchDayRepository.save(matchDay);
        playerRepository.save(player);
    }

    @Transactional(readOnly = true)
    @Override
    public PlayMatchDto findPlayMatchById(Long playMatchId) {
        PlayMatch playMatch = playMatchRepository.findById(playMatchId).get();
        PlayMatchDto map = modelMapper.map(playMatch, PlayMatchDto.class);
        return map;
    }

    @Transactional(readOnly = true)
    @Override
    public PlayMatchDto findPlayMatchByTwoIds(Long playerId, Long matchId) {
        PlayMatch playMatch = playMatchRepository.findByTwoIds(playerId, matchId);
        return modelMapper.map(playMatch, PlayMatchDto.class);
    }
}
