package com.viesant.M3S04_SuggestionBox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

  @Schema(description = "Class of the exception", example = "NotFoundException")
  private String exceptionClass;

  @Schema(description = "Error code", example = "404")
  private String code;

  @Schema(description = "Detailed error message", example = "Entity not found")
  private String message;

  @Schema(description = "List of invalid fields", example = "[\"title\", \"description\"]")
  private List<String> fields;
}
