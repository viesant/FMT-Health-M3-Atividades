package br.viesant.m3s06cleancode.Ex1_CodeReview;

import br.viesant.m3s06cleancode.Ex1_CodeReview.pack.Estoque;
import br.viesant.m3s06cleancode.Ex1_CodeReview.pack.EstoqueRepository;
import br.viesant.m3s06cleancode.Ex1_CodeReview.pack.Remedio;
import br.viesant.m3s06cleancode.Ex1_CodeReview.pack.RemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemedioService {
  @Autowired
  private RemedioRepository remedioRepository;
  @Autowired
  private EstoqueRepository estoqueRepository;

  public void saveRemedio(String nome, Integer dosagemEmMg) {
    //Salvar o remédio quando não encontrarmos
    Remedio remedio = new Remedio();
    remedio.setNome(nome);
    remedio.dosagemMg(dosagemEmMg);

    if (nome == null || nome.isEmpty() || nome.trim().isEmpty()) {
      throw new RuntimeException("Nome do remédio não pode ser vazio");
    } else if (dosagemEmMg == null || dosagemEmMg < 0) {
      if (dosagemEmMg == null) {
        throw new RuntimeException("Remédio precisa possuir dosagem");
      } else if (dosagemEmMg < 0) {
        throw new RuntimeException("Dosagem não pode ser negativa");
      }
    }

    remedioRepository.save(remedio);
  }

  public void addEstoque(Integer idR, Integer quantidade, String nomeR, Integer remedioDosagemMg) {
    //Se existe remedio eu adiciono o estoque
    if (remedioRepository.existe(idR)) {
      Remedio r = remedioRepository.getRemedioPorId(idR);

      Estoque eAtual = estoqueRepository.getEstoqueAtual(idR);

      if (eAtual == null) {
        //Cria um novo estoque quando não encontrou
        Estoque estoque = new Estoque();
        estoque.setIdRemedio(r.getId());
        estoque.setQuantidade(quantidade);

        if (quantidade < 0) {
          throw new RuntimeException("Quantidade adicionada no estoque não pode ser negativa");
        }

        estoqueRepository.save(estoque);
      } else {
        if (quantidade < 0) {
          throw new RuntimeException("Quantidade adicionada no estoque não pode ser negativa");
        }

        eAtual.setQuantidade(eAtual.getQuantidade() + quantidade);
        estoqueRepository.save(eAtual);
      }
    } else if (remedioRepository.existePorNome(nomeR)) {
      Remedio r = remedioRepository.getRemedioPorNome(nomeR);

      Estoque eAtual = estoqueRepository.getEstoqueAtual(r.getId());

      if (eAtual == null) {
        if (quantidade < 0) {
          throw new RuntimeException("Quantidade adicionada no estoque não pode ser negativa");
        }

        Estoque estoque = new Estoque();
        estoque.setQuantidade(quantidade);
        estoque.setIdRemedio(r.getId());


        estoqueRepository.save(estoque);
      } else {
        if (quantidade < 0) {
          throw new RuntimeException("Quantidade adicionada no estoque não pode ser negativa");
        }

        eAtual.setQuantidade(eAtual.getQuantidade() + quantidade);
        estoqueRepository.save(eAtual);
      }
    } else {
      //Salvar o remédio quando não encontrarmos
      Remedio r = new Remedio();
      r.setNome(nomeR);
      r.dosagemMg(remedioDosagemMg);

      if (nomeR == null || nomeR.isEmpty() || nomeR.trim().isEmpty()) {
        throw new RuntimeException("Nome do remédio não pode ser vazio");
      } else if (remedioDosagemMg == null || remedioDosagemMg < 0) {
        if (remedioDosagemMg == null) {
          throw new RuntimeException("Remédio precisa possuir dosagem");
        } else if (remedioDosagemMg < 0) {
          throw new RuntimeException("Dosagem não pode ser negativa");
        }
      }

      Integer id = remedioRepository.save(r);

      //Adicionar no estoque novo
      Estoque estoque = new Estoque();
      estoque.setIdRemedio(id);
      estoque.setQuantidade(quantidade);

      if (quantidade < 0) {
        throw new RuntimeException("Quantidade adicionada no estoque não pode ser negativa");
      }

      estoqueRepository.save(estoque);
    }
  }
}
