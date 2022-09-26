package com.acroriver.server.team.service;

import com.acroriver.server.team.dto.PlayerDto;
import com.acroriver.server.team.entity.enums.Position;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {

    public void createPlayer(PlayerDto player);

    public PlayerDto findPlayerDtoById(Long id);

    public List<PlayerDto> findAllPlayerDto();

    public List<PlayerDto> findPlayerDtoByPosition(Position position);

    public PlayerDto findPlayerDtoByBackNum(int backNum);

    public void changeBackNum(int backNum);

    // 경기 끝난 후, 자동으로 선수 기록 업데이트 해줘야 한다
    public void updatePlayerStats(Long id);

}
