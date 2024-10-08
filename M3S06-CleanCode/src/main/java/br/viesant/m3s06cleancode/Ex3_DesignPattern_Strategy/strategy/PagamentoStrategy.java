package br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.strategy;

import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.FormaPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.InformacoesPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.PessoaPaciente;

public interface PagamentoStrategy {
  FormaPagamento tipoDePagamento();

  void processaPagamento(PessoaPaciente paciente, InformacoesPagamento informacoesPagamento, Float valor);
}
