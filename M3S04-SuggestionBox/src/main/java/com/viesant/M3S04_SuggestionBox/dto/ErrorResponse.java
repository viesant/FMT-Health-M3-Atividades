package com.viesant.M3S04_SuggestionBox.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
  private String exceptionClass;

  private String code;
  private String message;

  private List<String> fields;
}
