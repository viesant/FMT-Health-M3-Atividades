package br.viesant.m3s06cleancode.Ex1_CodeReview.pack;

import jakarta.persistence.Entity;

@Entity
public class Remedio {

  private Long id;
  private String nome;
  private Integer dosagemEmMg;

  public void setNome(String nome) {
  }

  public void dosagemMg(Integer dosagemEmMg) {
  }

  public Integer getId() {
    return null;
  }
}
