package br.viesant.m3s06cleancode.Ex3_DesignPattern_Strategy.model;

public class InformacoesPagamento {

  private String cpfMedico;
  private Long idTransacaoPix;

  public String getCpfMedico() {
    return cpfMedico;
  }

  public void setCpfMedico(String cpfMedico) {
    this.cpfMedico = cpfMedico;
  }

  public Long getIdTransacaoPix() {
    return idTransacaoPix;
  }

  public void setIdTransacaoPix(Long idTransacaoPix) {
    this.idTransacaoPix = idTransacaoPix;
  }
}
