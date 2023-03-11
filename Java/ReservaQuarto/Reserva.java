package ReservaQuarto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import ReservaExcecao.ReservaException;

public class Reserva {

	private Integer numeroQuarto;
	private Date dataEntrada;
	private Date dataSaida;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reserva(Integer numeroQuarto, Date dataEntrada, Date dataSaida) throws ReservaException {
		if (dataEntrada.after(dataSaida)) {
			throw new ReservaException("Data de saída não pode ser menor que a data de entrada");
		}
		this.numeroQuarto = numeroQuarto;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	public Integer getNumeroQuarto() {
		return numeroQuarto;
	}

	public void setNumeroQuarto(Integer numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
	}

	public Date getCheckIn() {
		return dataEntrada;
	}

	public Date getCheckOut() {
		return dataSaida;
	}

	public long intervaloDias() {
		long duracao = dataSaida.getTime() - dataEntrada.getTime();
		return TimeUnit.DAYS.convert(duracao, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date dataEntrada, Date dataSaida) throws ReservaException {
		Date dataHoje = new Date();
		if (dataEntrada.before(dataHoje) || dataSaida.before(dataHoje)) {
			throw new ReservaException("Para alterar a data de reserva, precisa informar uma data futura");
		}
		if (dataEntrada.after(dataSaida)) {
			throw new ReservaException("Data de entrada maior que a data de saída");
		}
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}
	
	@Override
	public String toString() {
		return "Quarto: " + numeroQuarto
				+ ", Data de entrada: " + sdf.format(dataEntrada)
				+ ", Data de saída: " + sdf.format(dataSaida)+ ", "
				+ intervaloDias() + " dias";
	}
}

