package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.MatchDayDto;
import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.PlayMatch;
import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.entity.enums.MatchState;
import com.acroriver.server.team.repository.MatchDayRepository;
import com.acroriver.server.team.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MatchDayServiceImpl implements MatchDayService {

    private final MatchDayRepository matchDayRepository;
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createMatchDay(MatchDayDto matchDayDto) {
        MatchDay matchDay = MatchDay.builder()
                .matchDate(matchDayDto.getMatchDate())
                .awayName(matchDayDto.getAwayName())
                .state(MatchState.BEFORE)
                .build();
        matchDayRepository.save(matchDay);
    }

    @Override
    public List<MatchDayDto> findAll() {
        List<MatchDay> allMatchDay = matchDayRepository.findAll();
        return allMatchDay.stream()
                .map(p -> modelMapper.map(p, MatchDayDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MatchDayDto findOne(Long matchId) {
        MatchDay matchDay = matchDayRepository.findById(matchId).get();
        return modelMapper.map(matchDay, MatchDayDto.class);
    }

    @Override
    public List<MatchDayDto> findByMonth(int month) {
        List<MatchDay> matchDayList = matchDayRepository.findByMonth(month);
        return matchDayList.stream().
                map(p -> modelMapper.map(p, MatchDayDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public void updateMatchResult(Long matchId, MatchState matchState) {
        MatchDay matchDay = matchDayRepository.findById(matchId).get();
        matchDay.changeMatchState(matchState);
    }

    @Override
    public void createPlayMatch(Long matchId, Long playerId) {
        MatchDay matchDay = matchDayRepository.findById(matchId).get();
        Player player = playerRepository.findById(playerId).get();
        PlayMatch playMatch = new PlayMatch();

        playMatch.setMatchDay(matchDay);
        playMatch.setPlayer(player);

        player.addPlayMatch(playMatch);
        matchDay.addPlayMatch(playMatch);
    }
}
