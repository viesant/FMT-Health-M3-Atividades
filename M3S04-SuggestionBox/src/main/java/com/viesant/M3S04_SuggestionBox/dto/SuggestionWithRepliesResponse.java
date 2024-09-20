package com.viesant.M3S04_SuggestionBox.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.viesant.M3S04_SuggestionBox.entities.Suggestion;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SuggestionWithRepliesResponse extends SuggestionAbstract {

  private Long id;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedAt;

  List<ReplyResponse> replies;

  public SuggestionWithRepliesResponse(Suggestion suggestion) {
    BeanUtils.copyProperties(suggestion, this);
  }
}
