package br.com.exemplo.aula.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.exemplo.aula.controllers.dto.NutricionistaRequestDTO;
import br.com.exemplo.aula.controllers.dto.NutricionistaResponseDTO;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.repositories.NutricionistaRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NutricionistaServiceTest {

  Nutricionista nutricionista1;
  Nutricionista nutricionista2;

  @Mock private NutricionistaRepository repository;
  @InjectMocks private NutricionistaService service;

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
  @DisplayName("Busca todos os nutricionistas e retorna uma lista")
  void listarNutricionistas() {
    List<Nutricionista> nutricionistas = new ArrayList<>();
    nutricionistas.add(nutricionista1);
    nutricionistas.add(nutricionista2);

    when(repository.findAll()).thenReturn(nutricionistas);

    List<NutricionistaResponseDTO> response = service.listarNutricionistas();

    assertNotNull(response);
    assertEquals(nutricionistas.size(), response.size());

    // Verifica os atributos do primeiro nutricionista
    NutricionistaResponseDTO response1 = response.get(0);
    assertEquals(nutricionista1.getId(), response1.getId());
    assertEquals(nutricionista1.getNome(), response1.getNome());
    assertEquals(nutricionista1.getMatricula(), response1.getMatricula());
    assertEquals(nutricionista1.getTempoExperiencia(), response1.getTempoExperiencia());
    assertEquals(nutricionista1.getCrn(), response1.getCrn());
    assertEquals(nutricionista1.getEspecialidade(), response1.getEspecialidade());

    // Verifica os atributos do segundo nutricionista
    NutricionistaResponseDTO response2 = response.get(1);
    assertEquals(nutricionista2.getId(), response2.getId());
    assertEquals(nutricionista2.getNome(), response2.getNome());
    assertEquals(nutricionista2.getMatricula(), response2.getMatricula());
    assertEquals(nutricionista2.getTempoExperiencia(), response2.getTempoExperiencia());
    assertEquals(nutricionista2.getCrn(), response2.getCrn());
    assertEquals(nutricionista2.getEspecialidade(), response2.getEspecialidade());

    verify(repository).findAll();
  }

  @Test
  @DisplayName("Busca todos os nutricionistas e retorna uma lista vazia")
  void listarNutricionistasVazio() {
    when(repository.findAll()).thenReturn(Collections.emptyList());

    var resultado = service.listarNutricionistas();

    assertNotNull(resultado); // A lista deve existir
    assertTrue(resultado.isEmpty()); // Mas deve estar vazia

    verify(repository).findAll();
  }

  @Test
  @DisplayName("Busca nutricionista por Id e retorna um NutricionistaResponseDTO")
  void buscarNutricionista() {
    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista1));

    NutricionistaResponseDTO response = service.buscarNutricionista(1L);

    assertNotNull(response);

    assertEquals(nutricionista1.getId(), response.getId());
    assertEquals(nutricionista1.getNome(), response.getNome());
    assertEquals(nutricionista1.getMatricula(), response.getMatricula());
    assertEquals(nutricionista1.getTempoExperiencia(), response.getTempoExperiencia());
    assertEquals(nutricionista1.getCrn(), response.getCrn());
    assertEquals(nutricionista1.getEspecialidade(), response.getEspecialidade());

    verify(repository).findById(anyLong());
  }

  @Test
  @DisplayName("Busca nutricionista por Id inexistente e retorna Null")
  void buscarNutricionistaInexistente() {
    when(repository.findById(anyLong())).thenReturn(Optional.empty());

    NutricionistaResponseDTO responseDTO = service.buscarNutricionista(1L);

    assertNull(responseDTO);
    verify(repository).findById(anyLong());
  }

  @Test
  @DisplayName("Salva um novo nutricionista e retorna NutricionistaResponseDTO")
  void salvarNutricionista() {
    NutricionistaRequestDTO requestDTO = new NutricionistaRequestDTO();

    when(repository.save(any(Nutricionista.class))).thenReturn(nutricionista1);

    NutricionistaResponseDTO response = service.salvarNutricionista(requestDTO);

    assertNotNull(response);

    assertEquals(nutricionista1.getId(), response.getId());
    assertEquals(nutricionista1.getNome(), response.getNome());
    assertEquals(nutricionista1.getMatricula(), response.getMatricula());
    assertEquals(nutricionista1.getTempoExperiencia(), response.getTempoExperiencia());
    assertEquals(nutricionista1.getCrn(), response.getCrn());
    assertEquals(nutricionista1.getEspecialidade(), response.getEspecialidade());

    verify(repository).save(any(Nutricionista.class));
  }

  @Test
  @DisplayName("Tenta salvar um nutricionista com nome existente e retorna erro")
  void salvarNutricionistaDuplicado() {
    NutricionistaRequestDTO request = new NutricionistaRequestDTO();
    request.setNome(nutricionista1.getNome());

    when(repository.findByNome(anyString())).thenReturn(Optional.of(nutricionista1));

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> service.salvarNutricionista(request));
    assertEquals("Já existe cadastro de nutricionista com este nome.", exception.getMessage());
    verify(repository).findByNome(anyString());

    verify(repository, never()).save(any());
  }

  @Test
  @DisplayName("")
  void atualizarNutricionista() {
    NutricionistaRequestDTO request = new NutricionistaRequestDTO();

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista1));
    when(repository.save(any(Nutricionista.class))).thenReturn(nutricionista1);

    NutricionistaResponseDTO responseDTO = service.atualizarNutricionista(1L, request);

    assertNotNull(responseDTO);
    assertEquals(nutricionista1.getId(), responseDTO.getId());
    assertEquals(nutricionista1.getNome(), responseDTO.getNome());
    assertEquals(nutricionista1.getMatricula(), responseDTO.getMatricula());
    assertEquals(nutricionista1.getTempoExperiencia(), responseDTO.getTempoExperiencia());
    assertEquals(nutricionista1.getCrn(), responseDTO.getCrn());
    assertEquals(nutricionista1.getEspecialidade(), responseDTO.getEspecialidade());

    verify(repository).save(any(Nutricionista.class));
    verify(repository).findById(anyLong());
  }

  @Test
  @DisplayName("")
  void removerNutricionista() {
    Long nutricionistaId = 1L;

    service.removerNutricionista(nutricionistaId);

    verify(repository).deleteById(nutricionistaId);
  }
}
