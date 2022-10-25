package com.acroriver.server.error.controller;

import com.acroriver.server.error.entity.ErrorResponse;
import com.acroriver.server.error.entity.PlayerNotFoundException;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class PlayerExceptionHandler {
    @ExceptionHandler(PlayerNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handlePlayerNotFoundException(CommandAcceptanceException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("Player 를 찾을 수 없습니다.")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
