package com.viesant.M3S04_SuggestionBox.entities;

import com.viesant.M3S04_SuggestionBox.dto.SuggestionRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suggestions")
@Getter
@Setter
@NoArgsConstructor
public class Suggestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String title;

  @Column(nullable = false, length = 500)
  private String description;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "suggestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @OrderBy("repliedAt DESC")
  private List<Reply> replies = new ArrayList<>();

  public Suggestion(SuggestionRequest request) {
    BeanUtils.copyProperties(request, this);
  }
  /*
  Sugestão:
    id -> chave primária;
    titulo -> título da sugestão;
    descricao -> descrição completa da sugestão;
    dataEnvio -> data e hora em que a sugestão foi enviada;
    dataAtualizacao -> data e hora em que a sugestão sofreu alteração.
  */
}