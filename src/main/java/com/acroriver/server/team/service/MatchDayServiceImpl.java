package com.acroriver.server.team.service;

import com.acroriver.server.error.entity.MatchDayNotFoundException;
import com.acroriver.server.team.dto.matchday.MatchDayDetailDto;
import com.acroriver.server.team.dto.matchday.MatchDayDto;
import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.enums.MatchState;
import com.acroriver.server.team.repository.MatchDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MatchDayServiceImpl implements MatchDayService {

    private final MatchDayRepository matchDayRepository;

    public MatchDayDto buildMatchDayDto(MatchDay matchDay) {
        return MatchDayDto.builder()
                .matchId(matchDay.getId())
                .matchDate(matchDay.getMatchDate())
                .awayGoals(matchDay.getAwayGoals())
                .awayName(matchDay.getAwayName())
                .stadium(matchDay.getStadium())
                .state(matchDay.getState())
                .goals(matchDay.getGoals())
                .build();
    }

    @Transactional
    @Override
    public MatchDayDto createMatchDay(MatchDayDto matchDayDto) {
        MatchDay matchDay = MatchDay.builder()
                .matchDate(matchDayDto.getMatchDate())
                .awayName(matchDayDto.getAwayName())
                .state(matchDayDto.getState())
                .stadium(matchDayDto.getStadium())
                .goals(matchDayDto.getGoals())
                .awayGoals(matchDayDto.getAwayGoals())
                .build();

        MatchDay newMatchDay = matchDayRepository.save(matchDay);
        return buildMatchDayDto(newMatchDay);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MatchDayDto> findAll() {
        List<MatchDay> allMatchDay = matchDayRepository.findAll();
        return allMatchDay.stream()
                .map(this::buildMatchDayDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public MatchDayDto findOne(Long matchId) {
        MatchDay matchDay = matchDayRepository.findById(matchId).orElseThrow(MatchDayNotFoundException::new);
        return buildMatchDayDto(matchDay);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MatchDayDto> findByState(String state) {
        MatchState matchState = MatchState.valueOf(state);
        List<MatchDay> matchDayList = matchDayRepository.findByState(matchState);
        return matchDayList.stream().
                map(this::buildMatchDayDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<MatchDayDto> findByDate(int year, int month) {
        List<MatchDay> matchDayList = matchDayRepository.findByDate(year, month);
        return matchDayList.stream().
                map(this::buildMatchDayDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public MatchDayDto updateMatchInfo(MatchDayDto matchDayDto) {
        MatchDay matchDay = matchDayRepository.findById(matchDayDto.getMatchId()).orElseThrow(MatchDayNotFoundException::new);
        matchDay.changeMatchDate(matchDayDto.getMatchDate());
        matchDay.changeMatchAwayName(matchDayDto.getAwayName());
        matchDay.changeMatchState(matchDayDto.getState());
        matchDay.changeStadium(matchDayDto.getStadium());
        matchDay.changeAwayGoals(matchDayDto.getAwayGoals());
        matchDay.changeGoals(matchDayDto.getGoals());
        MatchDay save = matchDayRepository.save(matchDay);
        return buildMatchDayDto(save);
    }

    @Transactional(readOnly = true)
    @Override
    public MatchDayDto findNextMatch() {
        MatchDay nextMatch = matchDayRepository.findNextMatch();
        if (nextMatch == null)
            return null;
        return buildMatchDayDto(nextMatch);
    }

    @Transactional
    @Override
    public MatchDayDetailDto findMatchDetail(Long matchId) {
        MatchDay matchDay = matchDayRepository.findById(matchId).orElseThrow(MatchDayNotFoundException::new);
        return MatchDayDetailDto.builder()
                .matchDay(matchDay)
                .build();
    }

}
