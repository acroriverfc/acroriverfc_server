package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.MatchDayDto;
import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.enums.MatchState;
import com.acroriver.server.team.repository.MatchDayRepository;
import com.acroriver.server.team.repository.MatchDayRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MatchDayServiceImpl implements MatchDayService {

    private final MatchDayRepository matchDayRepository;
    private final MatchDayRepositorySupport matchDayRepositorySupport;
    private final ModelMapper modelMapper;

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
        List<MatchDay> matchDayList = matchDayRepositorySupport.findByMonth(month);
        return matchDayList.stream().
                map(p -> modelMapper.map(p, MatchDayDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public void updateMatchResult(Long matchId, MatchState matchState) {
        MatchDay matchDay = matchDayRepository.findById(matchId).get();
        matchDay.changeMatchState(matchState);
    }

}
