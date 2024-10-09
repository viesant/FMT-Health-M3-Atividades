package br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.factory;

import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.FormaPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.strategy.PagamentoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PagamentoStrategyFactory {

  @Autowired private List<PagamentoStrategy> strategies;

  public PagamentoStrategy getStrategyParaPagamento(FormaPagamento formaPagamento) {
    for (PagamentoStrategy strategy : strategies) {
      if (strategy.tipoDePagamento().equals(formaPagamento)) {
        return strategy;
      }
    }
    return null;
  }
}
