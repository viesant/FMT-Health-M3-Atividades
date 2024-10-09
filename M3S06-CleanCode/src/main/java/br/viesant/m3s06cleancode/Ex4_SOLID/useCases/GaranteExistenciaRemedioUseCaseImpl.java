package br.viesant.m3s06cleancode.Ex4_SOLID.useCases;

import br.viesant.m3s06cleancode.shared.Remedio;
import br.viesant.m3s06cleancode.shared.RemedioRepository;
import org.springframework.stereotype.Component;

@Component
public class GaranteExistenciaRemedioUseCaseImpl implements GaranteExistenciaRemedioUseCase {

  private final RemedioRepository remedioRepository;
  private final SalvarRemedioUseCase salvarRemedioUseCase;

  public GaranteExistenciaRemedioUseCaseImpl(
      RemedioRepository remedioRepository, SalvarRemedioUseCase salvarRemedioUseCase) {
    this.remedioRepository = remedioRepository;
    this.salvarRemedioUseCase = salvarRemedioUseCase;
  }

  @Override
  public Remedio obtenhaRemedio(Integer remedioId, String remedioNome, Integer remedioDosagemMg) {
    if (remedioRepository.existe(remedioId)) {
      return remedioRepository.getRemedioPorId(remedioId);
    }
    if (remedioRepository.existePorNome(remedioNome)) {
      return remedioRepository.getRemedioPorNome(remedioNome);
    }
    return salvarRemedioUseCase.salvar(remedioNome, remedioDosagemMg);
  }
}
