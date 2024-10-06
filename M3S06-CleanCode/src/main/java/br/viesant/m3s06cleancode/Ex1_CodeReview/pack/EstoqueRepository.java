package br.viesant.m3s06cleancode.Ex1_CodeReview.pack;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
  public Estoque getEstoqueAtual(Integer idR) {
  }
}
