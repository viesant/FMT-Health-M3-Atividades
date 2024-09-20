package com.viesant.M3S04_SuggestionBox.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viesant.M3S04_SuggestionBox.dto.ReplyRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "replies")
@Getter
@Setter
@NoArgsConstructor
public class Reply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String comment;
  private LocalDateTime repliedAt;

  @ManyToOne
  @JoinColumn(name = "suggestion_id", nullable = false)
  @JsonIgnore
  private Suggestion suggestion;

  public Reply(ReplyRequest request) {
    BeanUtils.copyProperties(request, this);
  }

  /*
  Comentário/Resposta:
    id -> chave primária;
    sugestaoId -> chave estrangeira referente à sugestão;
    texto -> texto do comentário ou resposta para a sugestão;
    dataEnvio -> data e hora em que o comentário/resposta foi enviada.
  */

}