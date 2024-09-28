package br.com.exemplo.aula.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.exemplo.aula.entities.Consulta;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.entities.Paciente;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("Test")
class ConsultaControllerIntegracaoTest {

  static Paciente paciente;
  static Nutricionista nutricionista;
  @Autowired MockMvc mvc;
  Consulta consulta;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    paciente =
        new Paciente(
            1L,
            "João Silva",
            LocalDate.of(1990, 1, 1),
            "111.111.111-11",
            "99999999",
            "joao@email.com");

    nutricionista =
        new Nutricionista(
            1L,
            "João Silva",
            "12345",
            3,
            "12345678",
            "Esportiva",
            Set.of("Certificação A", "Certificação B"));
  }

  @BeforeEach
  void setUp() {
    consulta = new Consulta(1L, nutricionista, paciente, LocalDate.now(), "observacoes");
  }

  @Test
  void salvarConsulta() throws Exception {
    mvc.perform(
            post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                                     {
                                         "idNutricionista": 1,
                                         "idPaciente": 1,
                                         "data": "10/10/2022",
                                         "observacoes":"auhehuahu"
                                     }"""))
        .andExpect(status().isOk());
  }

  /*
                      .content("""

  """)
  */

  @Test
  void listarConsultas() {}

  @Test
  void buscarConsulta() {}

  @Test
  void removerConsulta() {}
}
