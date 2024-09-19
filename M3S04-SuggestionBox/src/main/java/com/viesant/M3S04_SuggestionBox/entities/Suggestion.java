package com.viesant.M3S04_SuggestionBox.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "suggestions")
@Getter
@Setter
public class Suggestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String title;

  @Column(nullable = false, length = 500)
  private String description;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  /*
  Sugestão:
    id -> chave primária;
    titulo -> título da sugestão;
    descricao -> descrição completa da sugestão;
    dataEnvio -> data e hora em que a sugestão foi enviada;
    dataAtualizacao -> data e hora em que a sugestão sofreu alteração.
  */
}