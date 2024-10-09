package br.viesant.m3s06cleancode.Ex2_DesignPattern_Builder;

public class Medicamento {
  private String nome;
  private Integer dosagem;
  private String unidadeDosagem; // ou um enum DosagemEnum unidadeDosagem
  private String nomeLaboratorio;
  private String bula;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Integer getDosagem() {
    return dosagem;
  }

  public void setDosagem(Integer dosagem) {
    this.dosagem = dosagem;
  }

  public String getUnidadeDosagem() {
    return unidadeDosagem;
  }

  public void setUnidadeDosagem(String unidadeDosagem) {
    this.unidadeDosagem = unidadeDosagem;
  }

  public String getNomeLaboratorio() {
    return nomeLaboratorio;
  }

  public void setNomeLaboratorio(String nomeLaboratorio) {
    this.nomeLaboratorio = nomeLaboratorio;
  }

  public String getBula() {
    return bula;
  }

  public void setBula(String bula) {
    this.bula = bula;
  }

  @Override
  public String toString() {
    return "Medicamento{" + "nome='" + nome + '\'' + ", dosagem=" + dosagem + ", unidadeDosagem='" + unidadeDosagem + '\'' + ", nomeLaboratorio='" + nomeLaboratorio + '\'' + ", bula='" + bula + '\'' + '}';
  }
}
