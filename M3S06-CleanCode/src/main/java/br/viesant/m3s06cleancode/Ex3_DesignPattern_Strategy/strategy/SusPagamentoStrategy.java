package br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.strategy;

import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.exceptions.RegraDeNegocioException;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.FormaPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.InformacoesPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.PessoaPaciente;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.services.SusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SusPagamentoStrategy implements PagamentoStrategy {
  @Autowired private SusService susService;

  @Override
  public FormaPagamento tipoDePagamento() {
    return FormaPagamento.SUS;
  }

  @Override
  public void processaPagamento(
      PessoaPaciente paciente, InformacoesPagamento informacoesPagamento, Float valor) {
    if (paciente.getCpf() == null || paciente.getNumeroPis() == null) {
      throw new RegraDeNegocioException(
          "Informações obrigatórias nao preenchidas. Preencha Numero do PIS e CPF.");
    }

    susService.solicitaProcessoPagamento(paciente.getCpf(), paciente.getNumeroPis(), valor);
  }
}
