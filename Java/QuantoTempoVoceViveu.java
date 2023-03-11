import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class QuantoTempoVoceViveu {

  public static void main(String[] args) {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern(
      "dd/MM/yyyy - hh:mm:ss"
    );
    //FAZENDO OPERACOES/CALCULOS COM DATAS:
    LocalDateTime data1 = LocalDateTime.parse("2001-05-07T07:57:00.000");
    LocalDateTime data2 = LocalDateTime.now();

    //I N T E R V A L O    E N T R E    D U A S     D A T A S
    //PEGANDO ANOS, MESES, DIAS, HORAS, MINUTOS, SEGUNDOS DE *INTERVALO* ENTRE DUAS DATAS

    long diferencaEmAnos = Math.abs(ChronoUnit.YEARS.between(data1, data2));
    long diferencaEmMes = Math.abs(ChronoUnit.MONTHS.between(data1, data2));
    long diferencaEmSemanas = Math.abs(ChronoUnit.WEEKS.between(data1, data2));
    long intervaloEmDias = Math.abs(ChronoUnit.DAYS.between(data1, data2));
    long diferencaEmHoras = Math.abs(ChronoUnit.HOURS.between(data1, data2));
    long diferencaEmMinutos = Math.abs(
      ChronoUnit.MINUTES.between(data1, data2)
    );
    long diferencaEmSegundos = Math.abs(
      ChronoUnit.SECONDS.between(data1, data2)
    );

    System.out.println(
      "A pessoa que nasceu em: " +
      fmt.format(data1) +
      "\nViveu: " +
      diferencaEmAnos +
      " anos.\n" +
      diferencaEmMes +
      " meses.\n" +
      diferencaEmSemanas +
      " semanas.\n" +
      intervaloEmDias +
      " dias.\n" +
      diferencaEmHoras +
      " horas,\n" +
      diferencaEmMinutos +
      " minutos.\n" +
      diferencaEmSegundos +
      " segundos."
    );
  }
}
