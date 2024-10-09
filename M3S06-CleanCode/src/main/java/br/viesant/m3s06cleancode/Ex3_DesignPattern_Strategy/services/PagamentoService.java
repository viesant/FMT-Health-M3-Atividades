package br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.services;

import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.factory.PagamentoStrategyFactory;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.FormaPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.InformacoesPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.PessoaPaciente;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.strategy.PagamentoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

  @Autowired PagamentoStrategyFactory pagamentoStrategyFactory;

  public void processaPagamento(
      PessoaPaciente paciente,
      FormaPagamento formaPagamento,
      InformacoesPagamento informacoesPagamento,
      Float valor) {

    PagamentoStrategy pagamentoStrategy =
        pagamentoStrategyFactory.getStrategyParaPagamento(formaPagamento);
    pagamentoStrategy.processaPagamento(paciente, informacoesPagamento, valor);
  }
}
