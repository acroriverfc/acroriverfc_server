package com.acroriver.server.team.service;

import com.acroriver.server.error.entity.PlayerNotFoundException;
import com.acroriver.server.team.dto.PlayerDto;
import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.entity.enums.Position;
import com.acroriver.server.team.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    // ModelMapper 대신 이거
    public PlayerDto buildPlayerDto(Player player) {
        return PlayerDto.builder().playerId(player.getId())
                .playerName(player.getPlayerName())
                .backNum(player.getBackNum())
                .appearances(player.getAppearances())
                .birthDate(player.getBirthDate())
                .goals(player.getGoals())
                .assists(player.getAssists())
                .description(player.getDescription())
                .imageUrl(player.getImageUrl())
                .height(player.getHeight())
                .weight(player.getWeight())
                .position(player.getPosition())
                .build();
    }

    @Transactional
    @Override
    public PlayerDto createPlayer(PlayerDto player) {
        Player newPlayer = Player.builder()
                .playerName(player.getPlayerName())
                .birthDate(player.getBirthDate())
                .backNum(player.getBackNum())
                .imageUrl(player.getImageUrl())
                .position(player.getPosition())
                .description(player.getDescription())
                .weight(player.getWeight())
                .height(player.getHeight())
                .build();

        Player savePlayer = playerRepository.save(newPlayer);

        return buildPlayerDto(savePlayer);
    }

    @Transactional(readOnly = true)
    @Override
    public PlayerDto findPlayerDtoById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
        return buildPlayerDto(player);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlayerDto> findAllPlayerDto() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream()
                .map(this::buildPlayerDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlayerDto> findPlayerDtoByPosition(Position position) {
        List<Player> playerList = playerRepository.findByPositionOrderByBackNum(position);
        return playerList.stream()
                .map(this::buildPlayerDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public PlayerDto findPlayerDtoByBackNum(int backNum) {
        Player player = playerRepository.findByBackNum(backNum);
        return buildPlayerDto(player);
    }

    @Transactional
    @Override
    public PlayerDto updatePlayerInfo(PlayerDto playerDto) {
        Player player = playerRepository.findById(playerDto.getPlayerId())
                .orElseThrow(PlayerNotFoundException::new);
        player.changeImageUrl(playerDto.getImageUrl());
        player.changePlayerName(playerDto.getPlayerName());
        player.changeBirthDate(playerDto.getBirthDate());
        player.changeWeight(playerDto.getWeight());
        player.changeHeight(playerDto.getHeight());
        player.changeDescription(playerDto.getDescription());
        player.changePosition(playerDto.getPosition());
        player.changeBackNum(playerDto.getBackNum());
        playerRepository.save(player);

        return buildPlayerDto(player);
    }

    @Transactional(readOnly = true)
    @Override
    public List<List<PlayerDto>> findFivePlayers() {
        List<List<PlayerDto>> ret = new ArrayList<>();
        List<List<Player>> rank = playerRepository.findRank();
        log.info(rank.toString());
        for (List<Player> playerList : rank) {
            ret.add(playerList.stream().limit(5).
                    map(this::buildPlayerDto)
                    .collect(Collectors.toList()));
        }
        return ret;
    }


}
