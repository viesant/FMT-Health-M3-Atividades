package br.viesant.m3s06cleancode.Ex2_DesignPattern_Builder;

public class MedicamentoBuilder {
  private String nome;
  private Integer dosagem;
  private String unidadeDosagem; // ou um enum DosagemEnum unidadeDosagem
  private String nomeLaboratorio;
  private String bula;

  public static MedicamentoBuilder umMedicamento(){
    return new MedicamentoBuilder();
  }
  private MedicamentoBuilder(){}

  public MedicamentoBuilder comNome(String nome) {
    this.nome = nome;
    return this;
  }

  public MedicamentoBuilder naDosagem(int dosagem) {
    this.dosagem = dosagem;
    return this;
  }

  public MedicamentoBuilder mg() {
    this.unidadeDosagem = "Mg";
    return this;
  }

  public MedicamentoBuilder doLaboratorio(String laboratorio) {
    this.nomeLaboratorio = laboratorio;
    return this;
  }

  public MedicamentoBuilder comBula(String bula) {
    this.bula = bula;
    return this;
  }

  public Medicamento build() {
    Medicamento medicamento = new Medicamento();
    medicamento.setNome(nome);
    medicamento.setDosagem(dosagem);
    medicamento.setUnidadeDosagem(unidadeDosagem);
    medicamento.setNomeLaboratorio(nomeLaboratorio);
    medicamento.setBula(bula);
    return medicamento;
  }
}

/*  public static class Builder {
    private String nome;
    private Integer dosagem;
    private String unidadeDosagem; // ou um enum DosagemEnum unidadeDosagem
    private String nomeLaboratorio;
    private String bula;

    public Builder nome(String nome) {
      this.nome = nome;
      return this;
    }

    public Builder dosagem(Integer dosagem) {
      this.dosagem = dosagem;
      return this;
    }

    public Builder unidadeDosagem(String unidadeDosagem) {
      this.unidadeDosagem = unidadeDosagem;
      return this;
    }

    public Builder nomeLaboratorio(String nomeLaboratorio) {
      this.nomeLaboratorio = nomeLaboratorio;
      return this;
    }

    public Builder bula(String bula) {
      this.bula = bula;
      return this;
    }

    public Medicamento build() {
      return new Medicamento(this);
    }
  }*/