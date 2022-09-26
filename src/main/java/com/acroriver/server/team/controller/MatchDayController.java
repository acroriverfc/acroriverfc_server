package com.acroriver.server.team.controller;

import com.acroriver.server.team.dto.MatchDayDto;
import com.acroriver.server.team.service.MatchDayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchDayController {
    private final MatchDayServiceImpl matchDayService;

    // 모든 경기 조회
    @GetMapping("/matchDay/all")
    public List<MatchDayDto> findAllMatchDays() {
        return matchDayService.findAll();
    }

    // 경기 생성
    @PostMapping("/matchDay")
    public ResponseEntity<MatchDayDto> createMatch(@RequestBody MatchDayDto matchDayDto) {
        matchDayService.createMatchDay(matchDayDto);
        return new ResponseEntity<>(matchDayDto, HttpStatus.CREATED);
    }


}
