package com.viesant.M3S04_SuggestionBox.exceptions;

import com.viesant.M3S04_SuggestionBox.dto.ErrorResponse;
import com.viesant.M3S04_SuggestionBox.exceptions.notfound.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

  @Operation(
      summary = "Handles generic exceptions",
      description = "Returns a 500 Internal Server Error when an unhandled exception occurs.")
  @ApiResponse(
      responseCode = "500",
      description = "Internal Server Error",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
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

  @Operation(
      summary = "Handles NotFoundException",
      description = "Returns a 404 Not Found error when the entity cannot be found.")
  @ApiResponse(
      responseCode = "404",
      description = "Entity not found",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  @ResponseStatus(HttpStatus.NOT_FOUND)
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

  @Operation(
      summary = "Handles HttpMessageNotReadableException",
      description =
          "Returns a 400 Bad Request error when the request body is malformed or missing.")
  @ApiResponse(
      responseCode = "400",
      description = "Bad request",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException e) {
    return ResponseEntity.status(400)
        .body(
            ErrorResponse.builder()
                .exceptionClass(e.getClass().getSimpleName())
                .code("400")
                .message(e.getLocalizedMessage())
                .build());
  }
}
