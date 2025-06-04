import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.NumberFormat;
import java.util.Locale;

public class QuantoTempoVoceViveu {

  public static void main(String[] args) {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern(
      "dd/MM/yyyy - hh:mm:ss"
    );

    NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
    //FAZENDO OPERACOES/CALCULOS COM DATAS:
    LocalDateTime data1 = LocalDateTime.parse("2004-08-18T14:00:00.000");
    //LocalDateTime data2 = LocalDateTime.parse("2025-07-01T14:00:00.000");
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
    "A Pessoa que nasceu em: " +
    fmt.format(data1) +
    "\nViveu: " +
    nf.format(diferencaEmAnos) +
    " anos.\n" +
    nf.format(diferencaEmMes) +
    " meses.\n" +
    nf.format(diferencaEmSemanas) +
    " semanas.\n" +
    nf.format(intervaloEmDias) +
    " dias.\n" +
    nf.format(diferencaEmHoras) +
    " horas,\n" +
    nf.format(diferencaEmMinutos) +
    " minutos.\n" +
    nf.format(diferencaEmSegundos) +
    " segundos."
    );
  }
}
