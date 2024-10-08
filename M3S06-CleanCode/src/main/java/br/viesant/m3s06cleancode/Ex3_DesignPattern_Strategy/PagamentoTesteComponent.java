package br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy;

import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.FormaPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.InformacoesPagamento;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model.PessoaPaciente;
import br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoTesteComponent {

  @Autowired
  public PagamentoTesteComponent(PagamentoService service) {
    PessoaPaciente paciente1 = new PessoaPaciente();
    InformacoesPagamento pagamento1 = new InformacoesPagamento();
    service.processaPagamento(paciente1, FormaPagamento.CONVENIO_UNIMED, pagamento1, 50.0f);
  }
}
