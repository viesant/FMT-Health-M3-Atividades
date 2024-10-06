package br.viesant.m3s06cleancode.Ex1_CodeReview.pack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemedioRepository extends JpaRepository<Remedio, Long> {
  boolean existe(Integer idR);

  Remedio getRemedioPorId(Integer idR);

  Remedio getRemedioPorNome(String nomeR);

  boolean existePorNome(String nomeR);
}
