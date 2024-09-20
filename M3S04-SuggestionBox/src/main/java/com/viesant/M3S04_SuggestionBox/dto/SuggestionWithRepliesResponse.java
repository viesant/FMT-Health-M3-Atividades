package com.viesant.M3S04_SuggestionBox.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.viesant.M3S04_SuggestionBox.entities.Suggestion;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SuggestionWithRepliesResponse extends SuggestionAbstract {


  @Schema(description = "The unique ID of the suggestion", example = "1")
  private Long id;

  @Schema(
      description = "The date and time when the suggestion was created",
      example = "2024-09-20 10:30:00")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;

  @Schema(
      description = "The date and time when the suggestion was last updated",
      example = "2024-09-20 11:00:00")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedAt;

  @Schema(description = "List of replies associated with this suggestion")
  List<ReplyResponse> replies;

  public SuggestionWithRepliesResponse(Suggestion suggestion) {
    BeanUtils.copyProperties(suggestion, this);
  }
}
