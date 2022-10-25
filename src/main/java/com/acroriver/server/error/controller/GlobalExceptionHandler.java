package com.acroriver.server.error.controller;

import com.acroriver.server.error.entity.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(CommandAcceptanceException.class)
    protected ResponseEntity<ErrorResponse> handleCommandAcceptanceException(CommandAcceptanceException e) {
        log.error("handleCommandAcceptanceException", e);
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("Executing DDL 오류")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<ErrorResponse> handleSQLException(SQLException e) {
        log.error("SQLException", e);
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("SQL 오류")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ErrorResponse> handleNoElementException(NoSuchElementException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("해당 항목이 존재하지 않습니다.")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("Null Pointer Exception")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("No Such Element Exception")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
