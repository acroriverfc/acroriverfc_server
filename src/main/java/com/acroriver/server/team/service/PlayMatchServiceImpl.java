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

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayMatchServiceImpl implements PlayMatchService {

    private final PlayMatchRepository playMatchRepository;
    private final PlayerRepository playerRepository;
    private final MatchDayRepository matchDayRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addPlayerToMatch(Long playerId, Long matchId) {
        Player player = playerRepository.findById(playerId).get();
        MatchDay matchDay = matchDayRepository.findById(matchId).get();
        PlayMatch playMatch = PlayMatch.builder()
                .player(player)
                .matchDay(matchDay)
                .build();

        matchDay.addPlayMatch(playMatch);
        player.addPlayMatch(playMatch);
        playMatchRepository.save(playMatch);
    }

    @Override
    public PlayMatchDto findPlayMatchById(Long playMatchId) {
        PlayMatch playMatch = playMatchRepository.findById(playMatchId).get();
        return modelMapper.map(playMatch, PlayMatchDto.class);
    }

    @Override
    public void updatePlayMatchStats(Long playerId, Long matchId, int goal, int assists) {
        PlayMatch playMatch = playMatchRepository.findByTwoIds(playerId, matchId);
        playMatch.updateAssists(assists);
        playMatch.updateGoals(goal);
        playMatchRepository.save(playMatch);

        int totalGoals = 0;
        int totalAssists = 0;
        List<PlayMatch> playMatchList = playMatchRepository.findByPlayerId(playerId);
        for (PlayMatch match : playMatchList) {
            totalGoals += match.getGoals();
            totalAssists += match.getAssists();
        }
        Player player = playerRepository.findById(playerId).get();
        player.updateStats(playMatchList.size(), totalGoals, totalAssists);
        playerRepository.save(player);

    }

    @Override
    public PlayMatchDto findPlayMatchByTwoIds(Long playerId, Long matchId) {
        PlayMatch playMatch = playMatchRepository.findByTwoIds(playerId, matchId);
        return modelMapper.map(playMatch, PlayMatchDto.class);
    }
}
