package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.PlayerDto;
import com.acroriver.server.team.entity.enums.Position;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    public PlayerDto findById(Long id);

    public List<PlayerDto> findAll();

    public List<PlayerDto> findByPlayerPosition(Position position);

    public PlayerDto findByBackNum(int backNum);
}
