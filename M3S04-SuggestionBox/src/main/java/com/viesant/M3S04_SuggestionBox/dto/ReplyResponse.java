package com.viesant.M3S04_SuggestionBox.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.viesant.M3S04_SuggestionBox.entities.Reply;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class ReplyResponse extends ReplyAbstract {

  private Long id;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime repliedAt;

  private Long suggestionId;

  public ReplyResponse(Reply reply, Long id) {
    BeanUtils.copyProperties(reply, this);
    this.suggestionId = id;
  }
}
