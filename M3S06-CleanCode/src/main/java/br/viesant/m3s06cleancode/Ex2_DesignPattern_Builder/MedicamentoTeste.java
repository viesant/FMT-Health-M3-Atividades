package br.viesant.m3s06cleancode.Ex2_DesignPattern_Builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoTeste {

  @Autowired
  public MedicamentoTeste() {
    System.out.println(getDipirona().toString());
  }

  public Medicamento getDipirona() {

    String bulaDipirona =
        new StringBuilder()
            .append("Para que serve Dipirona Sódica?\n")
            .append("Como a Dipirona Sódica tem ação analgésica e antipirética, ")
            .append("ela serve para aliviar dores e também reduzir a febre no paciente.")
            .append("Além disso, é importante ressaltar que o remédio é bastante indicado ")
            .append("para dor com intensidade leve e moderada.\n")
            .append("Esse remédio também serve para tratar dores de dentes,")
            .append("além de cólicas menstruais e outras condições. ")
            .append("Por ser acessível, ele pode ser encontrado nas mais ")
            .append("diferentes redes de farmácias.\n")
            .toString();

    return MedicamentoBuilder.umMedicamento()
        .comNome("Dipirona Sódica 500mg")
        .naDosagem(500)
        .mg()
        .doLaboratorio("EMS")
        .comBula(bulaDipirona)
        .build();
  }

  /* Código dado pelo exercício:
  Medicamento dipirona = new Medicamento();
  dipirona.setNome("Dipirona Sódica 500mg");
  dipirona.setDosagem(500);
  dipirona.setUnidadeDosagem("mg");
  dipirona.setNomeLaboratorio("EMS");
  String bulaDipirona = "Para que serve Dipirona Sódica?\n" +
      "Como a Dipirona Sódica tem ação analgésica e antipirética, ela serve para aliviar dores e também reduzir a febre no paciente. Além disso, é importante ressaltar que o remédio é bastante indicado para dor com intensidade leve e moderada.\n"
      +
      "Esse remédio também serve para tratar dores de dentes, além de cólicas menstruais e outras condições. Por ser acessível, ele pode ser encontrado nas mais diferentes redes de farmácias.\n";
  dipirona.setBula(bulaDipirona);

  return dipirona;
   */
}
