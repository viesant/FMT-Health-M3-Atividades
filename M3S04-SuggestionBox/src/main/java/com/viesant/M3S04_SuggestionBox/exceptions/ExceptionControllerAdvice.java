package com.viesant.M3S04_SuggestionBox.exceptions;

import com.viesant.M3S04_SuggestionBox.dto.ErrorResponse;
import com.viesant.M3S04_SuggestionBox.exceptions.notfound.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handle(Exception e) {
    log.error("500: internal server error");

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ErrorResponse.builder()
                .exceptionClass(e.getClass().getSimpleName())
                .code("500")
                .message(e.getLocalizedMessage())
                .build());
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handle(NotFoundException e) {
    log.error("404: entity not found");

    return ResponseEntity.status(404)
        .body(
            ErrorResponse.builder()
                .exceptionClass(e.getClass().getSimpleName())
                .code("404")
                .message(e.getLocalizedMessage())
                .build());
  }
}
