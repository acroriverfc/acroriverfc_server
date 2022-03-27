package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.MatchDayDto;
import com.acroriver.server.team.entity.MatchDay;
import com.acroriver.server.team.entity.PlayMatch;
import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.entity.enums.MatchState;
import com.acroriver.server.team.repository.MatchDayRepository;
import com.acroriver.server.team.repository.MatchDayRepositorySupport;
import com.acroriver.server.team.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MatchDayServiceImpl implements MatchDayService {

    private final MatchDayRepository matchDayRepository;
    private final MatchDayRepositorySupport matchDayRepositorySupport;
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(MatchDay matchDay) {
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


    @Override
    public void updateMatchDayPlayer(Long matchId, Long playerId, int goals, int assists) {
        MatchDay matchDay = matchDayRepository.findById(matchId).get();
        Player player = playerRepository.findById(playerId).get();
        // 여기에 이제 출전경기를 추출해서 골 / 어시 업데이트
        // 추출하는 방법 : List 에서 player_id 랑 match_id 같은거 찾으면 되지 않을까?

        player.updateMatchInfo(goals, assists);

        playerRepository.save(player);
        matchDayRepository.save(matchDay);
    }

}
