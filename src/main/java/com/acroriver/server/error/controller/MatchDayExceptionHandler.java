package com.acroriver.server.error.controller;

import com.acroriver.server.error.entity.ErrorResponse;
import com.acroriver.server.error.entity.MatchDayNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MatchDayExceptionHandler {
    @ExceptionHandler(MatchDayNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleMatchDayNotFoundException(CommandAcceptanceException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("MatchDay 를 찾을 수 없습니다.")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
