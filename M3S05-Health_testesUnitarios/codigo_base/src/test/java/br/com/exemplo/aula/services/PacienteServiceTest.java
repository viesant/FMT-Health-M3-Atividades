package br.com.exemplo.aula.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.exemplo.aula.controllers.dto.PacienteRequestDTO;
import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.entities.Paciente;
import br.com.exemplo.aula.repositories.PacienteRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

  Paciente paciente1;
  Paciente paciente2;

  @Mock private PacienteRepository repository;
  @InjectMocks private PacienteService service;

  @BeforeEach
  void setUp() {
    paciente1 =
        new Paciente(
            1L,
            "João Silva",
            LocalDate.of(1990, 1, 1),
            "111.111.111-11",
            "99999999",
            "joao@email.com");

    paciente2 =
        new Paciente(
            2L,
            "Maria Souza",
            LocalDate.of(1985, 5, 5),
            "222.222.222-22",
            "88888888",
            "maria@email.com");
  }

  @Test
  @DisplayName("Busca todos os pacientes e retorna uma lista")
  void listarPacientes() {

    List<Paciente> pacientes = new ArrayList<>();

    pacientes.add(paciente1);
    pacientes.add(paciente2);

    when(repository.findAll()).thenReturn(pacientes);

    List<PacienteResponseDTO> resultado = service.listarPacientes();

    // Then: Verificar se a lista retornada não é nula
    assertNotNull(resultado);

    // Verificar se a lista tem o mesmo tamanho
    assertEquals(2, resultado.size());

    // Verificar se os atributos do primeiro paciente estão corretos
    PacienteResponseDTO responseDTO1 = resultado.get(0);
    assertEquals(paciente1.getId(), responseDTO1.getId());
    assertEquals(paciente1.getNome(), responseDTO1.getNome());
    assertEquals(paciente1.getDataNascimento(), responseDTO1.getDataNascimento());
    assertEquals(paciente1.getCpf(), responseDTO1.getCpf());
    assertEquals(paciente1.getTelefone(), responseDTO1.getTelefone());
    assertEquals(paciente1.getEmail(), responseDTO1.getEmail());

    // Verificar se os atributos do segundo paciente estão corretos
    PacienteResponseDTO responseDTO2 = resultado.get(1);
    assertEquals(paciente2.getId(), responseDTO2.getId());
    assertEquals(paciente2.getNome(), responseDTO2.getNome());
    assertEquals(paciente2.getDataNascimento(), responseDTO2.getDataNascimento());
    assertEquals(paciente2.getCpf(), responseDTO2.getCpf());
    assertEquals(paciente2.getTelefone(), responseDTO2.getTelefone());
    assertEquals(paciente2.getEmail(), responseDTO2.getEmail());

    // Verificar se o método do repositório foi chamado corretamente
    verify(repository).findAll();
  }

  @Test
  @DisplayName("Busca todos os pacientes e retorna uma lista vazia")
  void listarPacientesVazio() {
    when(repository.findAll()).thenReturn(Collections.emptyList());

    var resultado = service.listarPacientes();

    assertNotNull(resultado); // A lista deve existir
    assertTrue(resultado.isEmpty()); // Mas deve estar vazia

    verify(repository).findAll();
  }

  @Test
  @DisplayName("Busca paciente por Id e retorna um PacienteResponseDTO")
  void buscarPaciente() {
    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(paciente1));

    PacienteResponseDTO responseDTO = service.buscarPaciente(1L);

    assertNotNull(responseDTO);

    assertEquals(paciente1.getId(), responseDTO.getId());
    assertEquals(paciente1.getNome(), responseDTO.getNome());
    assertEquals(paciente1.getDataNascimento(), responseDTO.getDataNascimento());
    assertEquals(paciente1.getCpf(), responseDTO.getCpf());
    assertEquals(paciente1.getTelefone(), responseDTO.getTelefone());
    assertEquals(paciente1.getEmail(), responseDTO.getEmail());

    verify(repository).findById(anyLong());
  }

  @Test
  @DisplayName("Busca paciente por Id inexistente e retorna Null")
  void buscarPacienteInexistente() {
    when(repository.findById(anyLong())).thenReturn(Optional.empty());

    PacienteResponseDTO responseDTO = service.buscarPaciente(1L);

    assertNull(responseDTO);
    verify(repository).findById(anyLong());
  }

  @Test
  @DisplayName("Recebe um paciente e salva no repositório")
  void salvarPaciente() {
    PacienteRequestDTO requestDTO =
        new PacienteRequestDTO();

    when(repository.save(any(Paciente.class))).thenReturn(paciente1);

    PacienteResponseDTO responseDTO = service.salvarPaciente(requestDTO);

    assertNotNull(responseDTO);

    assertEquals(paciente1.getId(), responseDTO.getId());
    assertEquals(paciente1.getNome(), responseDTO.getNome());
    assertEquals(paciente1.getDataNascimento(), responseDTO.getDataNascimento());
    assertEquals(paciente1.getCpf(), responseDTO.getCpf());
    assertEquals(paciente1.getTelefone(), responseDTO.getTelefone());
    assertEquals(paciente1.getEmail(), responseDTO.getEmail());

    verify(repository).save(any(Paciente.class));
  }

  @Test
  @DisplayName("Busca paciente por Id e altera campos")
  void atualizarPaciente() {
    PacienteRequestDTO requestDTO =
            new PacienteRequestDTO();

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(paciente1));
    when(repository.save(any(Paciente.class))).thenReturn(paciente1);

    PacienteResponseDTO responseDTO = service.atualizarPaciente(1L, requestDTO);

    assertNotNull(responseDTO);

    assertEquals(paciente1.getId(), responseDTO.getId());
    assertEquals(paciente1.getNome(), responseDTO.getNome());
    assertEquals(paciente1.getDataNascimento(), responseDTO.getDataNascimento());
    assertEquals(paciente1.getCpf(), responseDTO.getCpf());
    assertEquals(paciente1.getTelefone(), responseDTO.getTelefone());
    assertEquals(paciente1.getEmail(), responseDTO.getEmail());

    verify(repository).save(any(Paciente.class));
    verify(repository).findById(anyLong());

  }

  @Test
  @DisplayName("Remover paciente por Id")
  void removerPaciente() {
    Long pacienteId = 1L;

    // Executa o método que remove o paciente
    service.removerPaciente(pacienteId);

    // Verifica se o método deleteById foi chamado corretamente com o ID fornecido
    verify(repository).deleteById(pacienteId);
  }
}
