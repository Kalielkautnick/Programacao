import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Datas-Tutorial {

  public static void main(String[] args) {
    //FORMATANDO DATAS PARA IMPRIMIR
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate data = LocalDate.now();

    //nesse caso, estou usando o formatador de datas para imprimir, e declarando dessas duas formas dá no mesmo
    //posso chamar o Formatter como parametro, ou passar a data como parametro para formatá-la.
    System.out.println("Data formatada: " + fmt.format(data));
    System.out.println("Data formatada: " + data.format(fmt));
    //********************************************************************************

    //INSTANCIANDO DATAS:
    //aqui estou instanciando a data de hoje, apenas ano-mes-dia
    LocalDate data1 = LocalDate.now();

    //aqui eu estou instanciando a data de hoje na hora atual do meu computador local(Brasil)-SC
    //ele instancia dd/MM/yyyy hh:mm:ss.mm
    LocalDateTime data2 = LocalDateTime.now();

    //aqui eu estou instanciando a data de hoje na hora global, fuso horario GMT de Londres(3 horas a frente do Brasil).
    Instant data3 = Instant.now();

    //aqui eu instancio uma data especifica através de uma String, instanciei o dia 18/08/2022
    LocalDate data4 = LocalDate.parse("2022-08-18");

    //aqui eu instancio uma data e horario especifico por String, COM hh:mm:ss.mm
    LocalDateTime data5 = LocalDateTime.parse("2022-08-18T10:23:15.405611111");

    //aqui eu instancio uma data especifica informando ano, mes e dia:
    LocalDate data6 = LocalDate.of(2022, 8, 18);
    //também dá pra instanciar desse mesmo jeito, ano, mes, dia, hora, minutos e segundos:
    LocalDateTime data7 = LocalDateTime.of(2022, 8, 18, 12, 30, 05);
    /**************************************************************************************/

    //RETORNANDO DADOS DAS DATAS:
    LocalDateTime data8 = LocalDateTime.parse("2022-08-18T10:23:15.405611111");
    int ano = data8.getYear(); //ANO = 2022
    int mes = data8.getMonthValue(); //MES = 8
    //Esse ai retorna no tipo DayOfWeek, qual o dia da semana de uma data, precisa importar
    DayOfWeek diaSemana = data8.getDayOfWeek(); //DIASEMANA = Thursday
    int diaDoMes = data8.getDayOfMonth(); //DIADOMES = 18
    int diaDoAno = data8.getDayOfYear(); //DIADOANO = 230 no ano de 2022 porque não é bissexto
    int hora = data8.getHour(); //HORA = 10
    int minuto = data8.getMinute(); //MINUTO = 23
    int segundos = data8.getSecond(); //SEGUNDOS = 15;
    int milesimos = data8.getNano(); //milesimos = 405611111;
    //*********************************************************************

    //FAZENDO OPERACOES/CALCULOS COM DATAS:
    LocalDateTime data10 = LocalDateTime.parse("2018-05-10T10:05:30.450");
    LocalDateTime data11 = LocalDateTime.parse("2022-08-18T18:15:45.450");

    //I N T E R V A L O    E N T R E    D U A S     D A T A S
    //PEGANDO ANOS, MESES, DIAS, HORAS, MINUTOS, SEGUNDOS DE *INTERVALO* ENTRE DUAS DATAS

    /*pra poder fazer essa atribuição, precisa usar o tipo LONG, e sendo dois LocalDate comparados, precisa fazer esse import:
import java.time.temporal.ChronoUnit; */
    long diferencaEmAnos = Math.abs(ChronoUnit.YEARS.between(data11, data10)); //R: 4 anos
    long diferencaEmMes = Math.abs(ChronoUnit.MONTHS.between(data10, data11)); //R: 51 meses
    long diferencaEmSemanas = Math.abs(
      ChronoUnit.WEEKS.between(data10, data11)
    ); //R: 223 semanas
    long intervaloEmDias = Math.abs(ChronoUnit.DAYS.between(data10, data11)); //R: 1.561 dias

    long diferencaEmHoras = Math.abs(ChronoUnit.HOURS.between(data10, data11)); //R: 37.464 horas
    long diferencaEmMinutos = Math.abs(
      ChronoUnit.MINUTES.between(data10, data11)
    ); //R: 2.247.840 minutos
    long diferencaEmSegundos = Math.abs(
      ChronoUnit.SECONDS.between(data10, data11)
    ); //R: 134.870.400 segundos
    long diferencaEmMilesimos = Math.abs(
      ChronoUnit.NANOS.between(data10, data11)
    ); //R: trilhões de milésimos

    //NESSE CASO EU ESTOU USANDO O MAT.ABS PARA PEGAR A DIFERENÇA ABSOLUTA ENTRE AS DATAS
    //SE A PRIMEIRA DATA FOR MENOR QUE A OUTRA, O RETORNO VAI SER UM NÚMERO NEGATIVO, MAS COM O MATH.ABS ELE PEGA
    //A DIFERENÇA ABSOLUTA, (POSITIVA);
    System.out.println("Diferença em anos: " + diferencaEmAnos);
    //*******************************************************************************************************

    if (data10.isAfter(data11)) {
      /*A OPERACAO ISAFTER, COMPARA SE A DATA10 VEM **DEPOIS** DA DATA 11
    NESSE CASO AQUI, É FALSO, A DATA10 É 2018 E A DATA11 É 2022*/
    }
    if (data10.isBefore(data11)) {
      /*A OPERACAO ISBEFORE, COMPARA SE A DATA10 VEM **ANTES** DA DATA 11
    NESSE CASO AQUI, É TRUE, A DATA10 É 2018 E A DATA11 É 2022*/
    }
    if (data10.isEqual(data11)) {
      /*A OPERACAO ISBEFORE, COMPARA SE A DATA10 É IGUAL A DATA 11
    NESSE CASO AQUI, É FALSE, A DATA10 É 2018 E A DATA11 É 2022*/
    }

    //INSTANCIANDO UMA DATA COM 2 ANOS A MENOS QUE OUTRA DATA
    LocalDateTime data12 = data11.minusYears(2); //2022-8-18 --> 2020-8-18

    //INSTANCIANDO UMA DATA COM 3 MESES A MAIS QUE OUTRA DATA
    LocalDateTime data13 = data11.plusMonths(3); //2022-8-18 --> 2022-11-18

    //INSTANCIANDO UMA DATA COM 1 ANO, 4 MESES E 10 DIAS A MAIS QUE OUTRA DATA
    LocalDateTime data14 = data11.plusYears(1).plusMonths(4).plusDays(10); //2022-8-18 --> 2023-12-28
  }
}
