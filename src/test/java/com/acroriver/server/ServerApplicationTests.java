package com.acroriver.server;

import com.acroriver.server.team.dto.MatchStatDto;
import com.acroriver.server.team.dto.PlayerDto;
import com.acroriver.server.team.dto.matchday.MatchDayDetailDto;
import com.acroriver.server.team.dto.matchday.MatchDayDto;
import com.acroriver.server.team.entity.enums.MatchState;
import com.acroriver.server.team.entity.enums.Position;
import com.acroriver.server.team.repository.MatchDayRepository;
import com.acroriver.server.team.repository.MatchStatRepository;
import com.acroriver.server.team.repository.PlayMatchRepository;
import com.acroriver.server.team.repository.PlayerRepository;
import com.acroriver.server.team.service.MatchDayService;
import com.acroriver.server.team.service.MatchStatService;
import com.acroriver.server.team.service.PlayMatchService;
import com.acroriver.server.team.service.PlayerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServerApplicationTests {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    MatchStatRepository matchStatRepository;
    @Autowired
    PlayMatchRepository playMatchRepository;
    @Autowired
    MatchDayRepository matchDayRepository;
    @Autowired
    PlayMatchService playMatchService;
    @Autowired
    PlayerService playerService;
    @Autowired
    MatchDayService matchDayService;
    @Autowired
    MatchStatService matchStatService;

    @Test
    public void MatchStatTest() {
        PlayerDto p = PlayerDto.builder()
                .height(178)
                .weight(69)
                .imageUrl("")
                .backNum(38)
                .playerName("김길동")
                .birthDate(LocalDate.now())
                .description("테스트용 선수")
                .position(Position.FW)
                .build();

        MatchDayDto m = MatchDayDto.builder()
                .awayName("테스트2")
                .stadium("테스트 구장")
                .matchDate(LocalDateTime.now())
                .state(MatchState.BEFORE)
                .build();

        PlayerDto savePlayer = playerService.createPlayer(p);
        MatchDayDto saveMatch = matchDayService.createMatchDay(m);

        playMatchService.addPlayerToMatch(savePlayer.getPlayerId(), saveMatch.getMatchId());
        matchStatService.createMatchStatWithoutAssist(savePlayer.getPlayerId(), saveMatch.getMatchId());
        MatchDayDetailDto matchDetail = matchDayService.findMatchDetail(saveMatch.getMatchId());
        for (MatchStatDto matchStatDto : matchDetail.getMatchStatDtoList()) {
            System.out.println("matchStatDto = " + matchStatDto.getGoalPlayerName());
            System.out.println("matchStatDto = " + matchStatDto.getAssistPlayerName());
        }
        PlayerDto player = playerService.findPlayerDtoById(savePlayer.getPlayerId());
        assertThat(player.getGoals()).isEqualTo(1);
    }

    @BeforeEach
    public void setUp() {
        matchStatRepository.deleteAll();
        playMatchRepository.deleteAll();
        matchDayRepository.deleteAll();
        playerRepository.deleteAll();
    }

    @AfterEach
    public void After() {
        matchStatRepository.deleteAll();
        playMatchRepository.deleteAll();
        matchDayRepository.deleteAll();
        playerRepository.deleteAll();
    }

    @Test
    public void Test2() {
        PlayerDto p = PlayerDto.builder()
                .height(178)
                .weight(69)
                .imageUrl("")
                .backNum(62)
                .playerName("김길동")
                .birthDate(LocalDate.now())
                .description("테스트용 선수")
                .position(Position.FW)
                .build();

        PlayerDto pp = PlayerDto.builder()
                .height(192)
                .weight(90)
                .imageUrl("")
                .backNum(7)
                .playerName("홀란드")
                .birthDate(LocalDate.now())
                .description("테스트 2")
                .position(Position.MF)
                .build();

        MatchDayDto m = MatchDayDto.builder()
                .awayName("테스트2")
                .stadium("테스트 구장")
                .matchDate(LocalDateTime.now())
                .state(MatchState.BEFORE)
                .build();

        PlayerDto p1 = playerService.createPlayer(p);
        PlayerDto p2 = playerService.createPlayer(pp);
        MatchDayDto saveMatch = matchDayService.createMatchDay(m);
        playMatchService.addPlayerToMatch(p1.getPlayerId(), saveMatch.getMatchId());
        playMatchService.addPlayerToMatch(p2.getPlayerId(), saveMatch.getMatchId());

        matchStatService.createMatchStatWithoutAssist(p1.getPlayerId(), saveMatch.getMatchId());
        matchStatService.createMatchStat(p2.getPlayerId(), p1.getPlayerId(), saveMatch.getMatchId());
        MatchDayDetailDto matchDetail = matchDayService.findMatchDetail(saveMatch.getMatchId());

        for (MatchStatDto matchStatDto : matchDetail.getMatchStatDtoList()) {
            System.out.println("matchStatDto.getGoalPlayerName() = " + matchStatDto.getGoalPlayerName());
            System.out.println("matchStatDto.getAssistPlayerName() = " + matchStatDto.getAssistPlayerName());
        }

        PlayerDto player1 = playerService.findPlayerDtoById(p1.getPlayerId());
        PlayerDto player2 = playerService.findPlayerDtoById(p2.getPlayerId());
        assertThat(player1.getGoals()).isEqualTo(1);
        assertThat(player1.getAssists()).isEqualTo(1);
        assertThat(player2.getGoals()).isEqualTo(1);
        assertThat(player2.getAssists()).isEqualTo(0);
        assertThat(matchDetail.getPlayMatchDtoList().size()).isEqualTo(2);
    }
}
