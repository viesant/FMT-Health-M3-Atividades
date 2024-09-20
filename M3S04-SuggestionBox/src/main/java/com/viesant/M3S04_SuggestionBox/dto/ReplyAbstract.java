package com.viesant.M3S04_SuggestionBox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public abstract class ReplyAbstract {
  @Schema(description = "The comment for the reply", example = "This is a sample comment")
  private String comment;
}
