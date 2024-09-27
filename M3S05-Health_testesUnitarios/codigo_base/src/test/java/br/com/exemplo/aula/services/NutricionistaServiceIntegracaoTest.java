package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.NutricionistaRequestDTO;
import br.com.exemplo.aula.controllers.dto.NutricionistaResponseDTO;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.repositories.NutricionistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class NutricionistaServiceIntegracaoTest {

  @Autowired private NutricionistaService service;

  @MockBean private NutricionistaRepository repository;

  private Nutricionista nutricionista1;
  private Nutricionista nutricionista2;

  @BeforeEach
  void setUp() {
    nutricionista1 =
        new Nutricionista(
            1L,
            "João Silva",
            "12345",
            3,
            "12345678",
            "Esportiva",
            Set.of("Certificação A", "Certificação B"));

    nutricionista2 =
        new Nutricionista(
            2L,
            "Maria Souza",
            "54321",
            5,
            "87654321",
            "Clínica",
            Set.of("Certificação C", "Certificação D"));
  }

  @Test
  @DisplayName("Deve listar nutricionistas e verificar se o método do repositório foi chamado")
  void listarNutricionistas() {
    List<Nutricionista> nutricionistas = List.of(nutricionista1, nutricionista2);
    when(repository.findAll()).thenReturn(nutricionistas);

    List<NutricionistaResponseDTO> response = service.listarNutricionistas();

    assertNotNull(response);
    assertEquals(nutricionistas.size(), response.size());
    assertEquals(nutricionista1.getId(), response.get(0).getId());

    verify(repository).findAll();
  }

  @Test
  @DisplayName("Deve salvar um nutricionista e verificar se o método save do repositório foi chamado")
  void salvarNutricionista() {
    NutricionistaRequestDTO requestDTO = new NutricionistaRequestDTO();
    
    when(repository.save(any(Nutricionista.class))).thenReturn(nutricionista1);

    NutricionistaResponseDTO response = service.salvarNutricionista(requestDTO);

    assertNotNull(response);
    assertEquals(nutricionista1.getId(), response.getId());
    assertEquals(nutricionista1.getNome(), response.getNome());

    verify(repository).save(any(Nutricionista.class));
  }
}
