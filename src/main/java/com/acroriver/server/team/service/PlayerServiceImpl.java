package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.PlayerDto;
import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.entity.enums.Position;
import com.acroriver.server.team.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void createPlayer(PlayerDto player) {
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

        playerRepository.save(newPlayer);
    }

    @Transactional(readOnly = true)
    @Override
    public PlayerDto findPlayerDtoById(Long id) {
        Player player = playerRepository.findById(id).get();
        return modelMapper.map(player, PlayerDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlayerDto> findAllPlayerDto() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream()
                .map(p -> modelMapper.map(p, PlayerDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlayerDto> findPlayerDtoByPosition(Position position) {
        List<Player> playerList = playerRepository.findByPositionOrderByBackNum(position);
        return playerList.stream()
                .map(p -> modelMapper.map(p, PlayerDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public PlayerDto findPlayerDtoByBackNum(int backNum) {
        Player player = playerRepository.findByBackNum(backNum);
        return modelMapper.map(player, PlayerDto.class);
    }

    @Transactional
    @Override
    public void updatePlayerInfo(PlayerDto playerDto) {
        Player player = playerRepository.findByBackNum(playerDto.getBackNum());
        player.changeImageUrl(playerDto.getImageUrl());
        player.changePlayerName(playerDto.getPlayerName());
        player.changeBirthDate(playerDto.getBirthDate());
        player.changeWeight(playerDto.getWeight());
        player.changeHeight(playerDto.getHeight());
        player.changeDescription(playerDto.getDescription());
        player.changePosition(playerDto.getPosition());
        player.changeBackNum(playerDto.getBackNum());
        playerRepository.save(player);
    }

    @Override
    public List<List<PlayerDto>> findFivePlayers() {
        List<List<PlayerDto>> ret = new ArrayList<>();
        List<List<Player>> rank = playerRepository.findRank();
        log.info(rank.toString());
        for (List<Player> playerList : rank) {
            ret.add(playerList.stream().limit(5).map(
                            p -> modelMapper.map(p, PlayerDto.class))
                    .collect(Collectors.toList()));
        }
        return ret;
    }


}
