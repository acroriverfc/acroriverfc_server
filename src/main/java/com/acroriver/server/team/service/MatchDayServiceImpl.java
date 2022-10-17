package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.MatchDayDto;
import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.enums.MatchState;
import com.acroriver.server.team.repository.MatchDayRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MatchDayServiceImpl implements MatchDayService {

    private final MatchDayRepository matchDayRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void createMatchDay(MatchDayDto matchDayDto) {
        MatchDay matchDay = MatchDay.builder()
                .matchDate(matchDayDto.getMatchDate())
                .awayName(matchDayDto.getAwayName())
                .state(matchDayDto.getState())
                .stadium(matchDayDto.getStadium())
                .goals(matchDayDto.getGoals())
                .awayGoals(matchDayDto.getAwayGoals())
                .build();

        matchDayRepository.save(matchDay);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MatchDayDto> findAll() {
        List<MatchDay> allMatchDay = matchDayRepository.findAll();
        return allMatchDay.stream()
                .map(p -> modelMapper.map(p, MatchDayDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public MatchDayDto findOne(Long matchId) {
        MatchDay matchDay = matchDayRepository.findById(matchId).get();
        return modelMapper.map(matchDay, MatchDayDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MatchDayDto> findByState(String state) {
        MatchState matchState = MatchState.valueOf(state);
        List<MatchDay> matchDayList = matchDayRepository.findByState(matchState);
        return matchDayList.stream().
                map(p -> modelMapper.map(p, MatchDayDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<MatchDayDto> findByDate(int year, int month) {
        List<MatchDay> matchDayList = matchDayRepository.findByDate(year, month);
        return matchDayList.stream().
                map(p -> modelMapper.map(p, MatchDayDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateMatchInfo(MatchDayDto matchDayDto) {
        MatchDay matchDay = matchDayRepository.findById(matchDayDto.getMatchId()).get();
        matchDay.changeMatchDate(matchDayDto.getMatchDate());
        matchDay.changeMatchAwayName(matchDayDto.getAwayName());
        matchDay.changeMatchState(matchDayDto.getState());
        matchDay.changeStadium(matchDayDto.getStadium());
        matchDay.changeAwayGoals(matchDayDto.getAwayGoals());
        matchDay.changeGoals(matchDayDto.getGoals());
        matchDayRepository.save(matchDay);
    }

    @Override
    public MatchDayDto findNextMatch() {
        MatchDay nextMatch = matchDayRepository.findNextMatch();
        return modelMapper.map(nextMatch, MatchDayDto.class);
    }

}
