package com.viesant.M3S04_SuggestionBox.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "replies")
@Getter
@Setter
public class Reply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String comment;
  private LocalDateTime repliedAt;

  @OneToOne
  @JoinColumn(name = "suggestion_id", nullable = false)
  private Suggestion suggestion;

  /*
  Comentário/Resposta:
    id -> chave primária;
    sugestaoId -> chave estrangeira referente à sugestão;
    texto -> texto do comentário ou resposta para a sugestão;
    dataEnvio -> data e hora em que o comentário/resposta foi enviada.
  */

}