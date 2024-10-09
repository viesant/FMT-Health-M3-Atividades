package br.viesant.m3s06cleancode.Ex4_SOLID.useCases;

public interface AdicionarEstoqueUseCase {
  void adicionar(Integer quantidade, Integer remedioId, String remedioNome, Integer remedioDosagemMg);
}
