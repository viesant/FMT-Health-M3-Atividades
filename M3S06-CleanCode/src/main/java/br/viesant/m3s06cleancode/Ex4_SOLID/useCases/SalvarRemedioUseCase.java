package br.viesant.m3s06cleancode.Ex4_SOLID.useCases;

import br.viesant.m3s06cleancode.shared.Remedio;

public interface SalvarRemedioUseCase {
  Remedio salvar(String nome, Integer dosagemMg);
}
