package br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.strategy;

import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.exceptions.RegraDeNegocioException;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.FormaPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.InformacoesPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.PessoaPaciente;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.services.UnimedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvenioUnimedPagamentoStrategy implements PagamentoStrategy {

  @Autowired private UnimedService unimedService;

  @Override
  public FormaPagamento tipoDePagamento() {
    return FormaPagamento.CONVENIO_UNIMED;
  }

  @Override
  public void processaPagamento(
      PessoaPaciente paciente, InformacoesPagamento informacoesPagamento, Float valor) {
    if (paciente.getNumeroConvenio() == null || paciente.getCpf() == null) {
      throw new RegraDeNegocioException(
          "Informações obrigatórias nao preenchidas. Preencha Numero de Convenio e CPF.");
    }
    unimedService.processaPagamento(
        paciente.getCpf(), paciente.getNumeroConvenio(), informacoesPagamento.getCpfMedico());
  }
}
