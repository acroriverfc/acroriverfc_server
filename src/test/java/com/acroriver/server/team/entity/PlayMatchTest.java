package com.acroriver.server.team.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@SpringBootTest
public class PlayMatchTest {
    @Test
    public void test() {
        System.out.println("Now : " + new Date());
        System.out.println(LocalDateTime.now());
    }

}