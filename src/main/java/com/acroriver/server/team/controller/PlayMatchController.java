package com.acroriver.server.team.controller;

import com.acroriver.server.team.service.PlayMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlayMatchController {

    private final PlayMatchService playMatchService;

    @PostMapping("/playMatch")
    public ResponseEntity<String> addPlayMatch(@RequestParam String playerId, @RequestParam String matchId) {
        playMatchService.addPlayerToMatch(Long.parseLong(playerId), Long.parseLong(matchId));
        return new ResponseEntity<>("성공적으로 추가 되었습니다.", HttpStatus.CREATED);
    }
}
