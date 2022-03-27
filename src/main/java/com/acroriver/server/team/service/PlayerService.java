package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.PlayerDto;
import com.acroriver.server.team.entity.Player;
import com.acroriver.server.team.entity.enums.Position;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {

    public void save(Player player);

    public PlayerDto findPlayerDtoById(Long id);

    public List<PlayerDto> findAllPlayerDto();

    public List<PlayerDto> findPlayerDtoByPosition(Position position);

    public PlayerDto findPlayerDtoByBackNum(int backNum);


}
