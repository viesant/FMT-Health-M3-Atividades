package br.com.exemplo.aula.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.exemplo.aula.controllers.dto.PacienteRequestDTO;
import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.services.PacienteService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PacienteController.class)
@AutoConfigureMockMvc
@ActiveProfiles("Test")
class PacienteControllerTest {

  @Autowired MockMvc mvc;

  @MockBean PacienteService service;

  private PacienteResponseDTO paciente1;
  private PacienteResponseDTO paciente2;

  @BeforeEach
  void setUp() {
    paciente1 =
        new PacienteResponseDTO(
            1L,
            "João Silva",
            LocalDate.of(1990, 1, 1),
            "111.111.111-11",
            "99999999",
            "joao@email.com");
    paciente2 =
        new PacienteResponseDTO(
            2L,
            "Maria Souza",
            LocalDate.of(1985, 5, 5),
            "222.222.222-22",
            "88888888",
            "maria@email.com");
  }

  @Test
  @DisplayName("POST /pacientes - Salva novo paciente")
  void salvarPaciente() throws Exception {
    when(service.salvarPaciente(any(PacienteRequestDTO.class))).thenReturn(paciente1);

    mvc.perform(
            post("/pacientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                             {
                                 "nome": "texto",
                                 "dataNascimento": "10/10/1010",
                                 "cpf": "texto",
                                 "telefone": "texto",
                                 "email": "texto",
                                 "idEndereco": 1
                             }"""))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(paciente1.getId()))
        .andExpect(jsonPath("$.nome").value(paciente1.getNome()));

    // Verifica se o serviço foi chamado
    verify(service).salvarPaciente(any(PacienteRequestDTO.class));
  }

  @Test
  @DisplayName("GET /pacientes - Lista todos os pacientes")
  void listarPacientes() throws Exception {
    List<PacienteResponseDTO> pacientes = Arrays.asList(paciente1, paciente2);

    when(service.listarPacientes()).thenReturn(pacientes);

    mvc.perform(get("/pacientes"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id").value(paciente1.getId()))
        .andExpect(jsonPath("$[0].nome").value(paciente1.getNome()))
        .andExpect(jsonPath("$[1].id").value(paciente2.getId()))
        .andExpect(jsonPath("$[1].nome").value(paciente2.getNome()));

    verify(service).listarPacientes();
  }

  @Test
  void search() throws Exception {
    when(service.buscarPaciente(anyLong())).thenReturn(paciente1);

    mvc.perform(get("/pacientes/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(paciente1.getId()))
        .andExpect(jsonPath("$.nome").value(paciente1.getNome()));

    verify(service).buscarPaciente(anyLong());
  }

  @Test
  @DisplayName("DELETE /pacientes/{id} - Remove paciente")
  void remove() throws Exception {
    doNothing().when(service).removerPaciente(anyLong());

    mvc.perform(delete("/pacientes/1"))
            .andExpect(status().isNoContent());

    // Verifica se o serviço foi chamado corretamente
    verify(service).removerPaciente(anyLong());
  }
  @Test
  void update() throws Exception {
    when(service.atualizarPaciente(anyLong(), any(PacienteRequestDTO.class))).thenReturn(paciente1);

    mvc.perform(
            put("/pacientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                             {
                                 "nome": "texto",
                                 "dataNascimento": "10/10/1010",
                                 "cpf": "texto",
                                 "telefone": "texto",
                                 "email": "texto",
                                 "idEndereco": 1
                             }"""))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(paciente1.getId()))
        .andExpect(jsonPath("$.nome").value(paciente1.getNome()));

    // Verifica se os serviços foram chamados
    verify(service).atualizarPaciente(anyLong(), any(PacienteRequestDTO.class));
  }
}
