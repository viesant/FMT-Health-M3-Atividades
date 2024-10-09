package br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.strategy;

import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.FormaPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.InformacoesPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.PessoaPaciente;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.services.BacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PixPagamentoStrategy implements PagamentoStrategy {
  @Autowired private BacenService bacenService;

  @Override
  public FormaPagamento tipoDePagamento() {
    return FormaPagamento.PIX;
  }

  @Override
  public void processaPagamento(
      PessoaPaciente paciente, InformacoesPagamento informacoesPagamento, Float valor) {
    if (informacoesPagamento.getIdTransacaoPix() != null) {
      bacenService.processaPix(informacoesPagamento.getIdTransacaoPix(), valor);
    } else {
      bacenService.geraChavePixParaPagamento(paciente.getChavePix(), valor);
    }
  }
}
