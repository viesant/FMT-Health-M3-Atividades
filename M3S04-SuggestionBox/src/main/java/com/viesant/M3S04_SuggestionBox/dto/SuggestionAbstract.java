package com.viesant.M3S04_SuggestionBox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public abstract class SuggestionAbstract {
  @Schema(description = "The title of the suggestion", example = "Improve Office Lighting")
  private String title;

  @Schema(
      description = "The detailed description of the suggestion",
      example = "We should consider upgrading the lighting system for better energy efficiency")
  private String description;
}
