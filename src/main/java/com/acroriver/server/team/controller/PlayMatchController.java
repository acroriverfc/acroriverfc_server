package com.acroriver.server.team.controller;

import com.acroriver.server.team.dto.PlayMatchDto;
import com.acroriver.server.team.service.PlayMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlayMatchController {

    private final PlayMatchService playMatchService;

    @PostMapping("/playMatch")
    public ResponseEntity<String> addPlayMatch(@RequestParam("playerId") Long playerId, @RequestParam("matchId") Long matchId) {
        playMatchService.addPlayerToMatch(playerId, matchId);
        return new ResponseEntity<>("성공적으로 추가 되었습니다.", HttpStatus.CREATED);
    }

    @GetMapping("/playMatch")
    public ResponseEntity<PlayMatchDto> findPlayMatch(@RequestParam String playerId, @RequestParam String matchId) {
        // QUERY DSL로 쿼리문 만들어서 사용해야 한다.
        Long pid = Long.parseLong(playerId);
        Long mid = Long.parseLong(matchId);
        return new ResponseEntity<>(playMatchService.findPlayMatchByTwoIds(pid, mid), HttpStatus.ACCEPTED);
    }

    @PutMapping("/playMatch")
    public ResponseEntity<PlayMatchDto> updatePlayMatch(@RequestBody PlayMatchDto playMatchDto) {
        PlayMatchDto updatePlayMatch = playMatchService.updatePlayMatchStats(playMatchDto.getPlayerId(), playMatchDto.getMatchId(), playMatchDto.getGoals(), playMatchDto.getAssists());
        return new ResponseEntity<>(updatePlayMatch, HttpStatus.CREATED);
    }
}
