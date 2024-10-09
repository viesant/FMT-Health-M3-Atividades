package br.viesant.m3s06cleancode.Ex4_SOLID;

public class ParametroInvalidoException extends RuntimeException {
  public ParametroInvalidoException(String campo, String mensagem) {
    super("Parâmetro invalido: " + campo + ": " + mensagem);
  }
}
