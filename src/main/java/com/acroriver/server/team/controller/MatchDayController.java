package com.acroriver.server.team.controller;

import com.acroriver.server.team.dto.MatchStatDto;
import com.acroriver.server.team.dto.MatchStatRequestDto;
import com.acroriver.server.team.dto.matchday.MatchDayDetailDto;
import com.acroriver.server.team.dto.matchday.MatchDayDto;
import com.acroriver.server.team.service.MatchDayService;
import com.acroriver.server.team.service.MatchStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchDayController {
    private final MatchDayService matchDayService;
    private final MatchStatService matchStatService;

    // 모든 경기 조회
    @GetMapping("/matchDay/all")
    public List<MatchDayDto> findAllMatchDays() {
        return matchDayService.findAll();
    }

    // 경기 생성
    @PostMapping("/matchDay")
    public ResponseEntity<MatchDayDto> createMatch(@RequestBody MatchDayDto matchDayDto) {
        MatchDayDto newMatchDayDto = matchDayService.createMatchDay(matchDayDto);
        return new ResponseEntity<>(newMatchDayDto, HttpStatus.CREATED);
    }

    // 날짜 기준 경기 조회
    @GetMapping("/matchDay")
    public ResponseEntity<List<MatchDayDto>> findMatchDayByDate(@RequestParam("year") String year, @RequestParam("month") String month) {
        List<MatchDayDto> matchDayList = matchDayService.findByDate(Integer.parseInt(year), Integer.parseInt(month));
        return new ResponseEntity<>(matchDayList, HttpStatus.OK);
    }

    // 경기 상태 기준 경기 조회
    @GetMapping("/matchDay/state/{state}")
    public ResponseEntity<List<MatchDayDto>> findMatchDayByState(@PathVariable String state) {
        List<MatchDayDto> matchDayList = matchDayService.findByState(state);
        return new ResponseEntity<>(matchDayList, HttpStatus.OK);
    }

    // 현재 날짜 기준으로 가장 가까운 경기 조회
    @GetMapping("/matchDay/next")
    public ResponseEntity<MatchDayDto> findNextMatch() {
        MatchDayDto nextMatch = matchDayService.findNextMatch();
        return new ResponseEntity<>(nextMatch, HttpStatus.OK);
    }

    // 경기 상세 정보
    @GetMapping("/matchDay/matchId/{matchId}")
    public ResponseEntity<MatchDayDetailDto> findMatchDetail(@PathVariable Long matchId) {
        MatchDayDetailDto matchDetail = matchDayService.findMatchDetail(matchId);
        return new ResponseEntity<>(matchDetail, HttpStatus.OK);
    }

    // 경기 정보 업데이트
    @PutMapping("/matchDay")
    public ResponseEntity<MatchDayDto> updateMatch(@RequestBody MatchDayDto matchDayDto) {
        MatchDayDto updateMatchInfo = matchDayService.updateMatchInfo(matchDayDto);
        return new ResponseEntity<>(updateMatchInfo, HttpStatus.OK);
    }

    // 골/어시 기록 업데이트
    @PostMapping("/matchDay/matchId/{matchId}")
    public ResponseEntity<MatchStatDto> updateMatchStat(@PathVariable Long matchId, @RequestBody MatchStatRequestDto matchStatRequestDto) {
        MatchStatDto matchStat = matchStatService.createMatchStat(matchStatRequestDto.getGoalId(), matchStatRequestDto.getAssistId(), matchId);
        return new ResponseEntity<>(matchStat, HttpStatus.CREATED);
    }
}
