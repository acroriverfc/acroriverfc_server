package com.acroriver.server.team.controller;

import com.acroriver.server.team.dto.PlayerDto;
import com.acroriver.server.team.entity.enums.Position;
import com.acroriver.server.team.service.PlayerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PlayerController {
    private final PlayerServiceImpl playerService;

    // 모든 플레이어 조회
    @GetMapping("/player/all")
    public List<PlayerDto> findAllPlayer() {
        return playerService.findAllPlayerDto();
    }

    // 등번호로 플레이어 조회
    @GetMapping("/player/{backNum}")
    public ResponseEntity<PlayerDto> findPlayerByBackNum(@PathVariable int backNum) {
        PlayerDto findPlayer = playerService.findPlayerDtoByBackNum(backNum);
        return new ResponseEntity<>(findPlayer, HttpStatus.OK);
    }

    @GetMapping("/player/position/{position}")
    public List<PlayerDto> findPlayerByPosition(@PathVariable String position) {
        position = position.toUpperCase();
        return playerService.findPlayerDtoByPosition(Position.valueOf(position));
    }

    // 플레이어 생성
    @PostMapping("/player")
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto) {
        PlayerDto newPlayer = playerService.createPlayer(playerDto);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    // 플레이어 정보 업데이트
    @PutMapping("/player")
    public ResponseEntity<PlayerDto> updatePlayerInfo(@RequestBody PlayerDto playerDto) {
        PlayerDto updatePlayer = playerService.updatePlayerInfo(playerDto);
        return new ResponseEntity<>(updatePlayer, HttpStatus.OK);
    }

    // 득점왕, 도움왕, 출전왕 5명 받아오기
    @GetMapping("/player/rank")
    public ResponseEntity<List<List<PlayerDto>>> findPlayerRank() {
        List<List<PlayerDto>> fivePlayers = playerService.findFivePlayers();
        return new ResponseEntity<>(fivePlayers, HttpStatus.OK);
    }
}
