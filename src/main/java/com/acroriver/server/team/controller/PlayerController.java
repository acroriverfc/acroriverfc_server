package com.acroriver.server.team.controller;

import com.acroriver.server.team.dto.PlayerDto;
import com.acroriver.server.team.service.PlayerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
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

    // 플레이어 생성
    @PostMapping("/player")
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto) {
        playerService.createPlayer(playerDto);
        return new ResponseEntity<>(playerDto, HttpStatus.CREATED);
    }
}
