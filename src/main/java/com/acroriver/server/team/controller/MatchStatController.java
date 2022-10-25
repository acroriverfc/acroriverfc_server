package com.acroriver.server.team.controller;

import com.acroriver.server.team.service.MatchStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatchStatController {

    private final MatchStatService matchStatService;

}
