package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.PlayerDto;
import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.entity.enums.Position;
import com.acroriver.server.team.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;


    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public PlayerDto findPlayerDtoById(Long id) {
        Player player = playerRepository.findById(id).get();
        return modelMapper.map(player, PlayerDto.class);
    }

    @Override
    public List<PlayerDto> findAllPlayerDto() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream()
                .map(p -> modelMapper.map(p, PlayerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerDto> findPlayerDtoByPosition(Position position) {
        List<Player> playerList = playerRepository.findByPosition(position);
        return playerList.stream()
                .map(p -> modelMapper.map(p, PlayerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDto findPlayerDtoByBackNum(int backNum) {
        Player player = playerRepository.findByBackNum(backNum);
        return modelMapper.map(player, PlayerDto.class);
    }
}
