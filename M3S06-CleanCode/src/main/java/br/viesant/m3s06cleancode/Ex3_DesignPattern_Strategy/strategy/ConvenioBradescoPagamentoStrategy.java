package br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.strategy;

import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.exceptions.RegraDeNegocioException;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.FormaPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.InformacoesPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.PessoaPaciente;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.services.BradescoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvenioBradescoPagamentoStrategy implements PagamentoStrategy {

  @Autowired private BradescoService bradescoService;

  @Override
  public FormaPagamento tipoDePagamento() {
    return FormaPagamento.CONVENIO_BRADESCO;
  }

  @Override
  public void processaPagamento(
      PessoaPaciente paciente, InformacoesPagamento informacoesPagamento, Float valor) {
    if (paciente.getCpf() == null) {
      throw new RegraDeNegocioException("Informaão obrigatórias nao preenchidas. Preencha o CPF.");
    }
    bradescoService.processaPagamento(
        paciente.getCpf(), informacoesPagamento.getCpfMedico(), valor);
  }
}
