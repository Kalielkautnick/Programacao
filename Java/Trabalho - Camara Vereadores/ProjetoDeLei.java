import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class ProjetoDeLei {

	private String titulo;
	private LocalDate dataApresentacao;
	private LocalDate dataAprovacao;
	private Integer numeroProjeto;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public ProjetoDeLei(Integer numeroProjeto, String titulo, LocalDate dataApresentacao, LocalDate dataAprovacao) {
		this.setTitulo(titulo);
		this.setDataApresentacao(dataApresentacao);
		this.setDataAprovacao(dataAprovacao);
		this.setNumeroProjeto(numeroProjeto);
	}
	
	public int getNumeroProjeto() {
		return numeroProjeto;
	}
	
	public void setNumeroProjeto(int id) {
		if (id > 0) {
		this.numeroProjeto = id;
		} else if (id <= 0) {
			throw new IllegalArgumentException("Número do projeto instanciado não é valido.");
		} else {
			throw new InputMismatchException("Número do projeto não pode conter letras.");
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {

		if (titulo != null && !titulo.isBlank()) {
			this.titulo = titulo;
		} else {
			throw new NullPointerException("Titulo nao preenchido.");
		}
	}

	public LocalDate getDataApresentacao() {
		return dataApresentacao;
	}

	public void setDataApresentacao(LocalDate dataApresentacao) {
		if (dataApresentacao == null) {
			throw new NullPointerException("Data invalida");
		} else if (this.dataAprovacao == null || !this.dataAprovacao.isBefore(dataApresentacao)) {
			this.dataApresentacao = dataApresentacao;
		} else {
			throw new IllegalArgumentException("Data de apresentação não pode ser superior a data de aprovação");
		}
	}

	public LocalDate getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(LocalDate dataAprovacao) {
		if (dataAprovacao == null) {
			this.dataAprovacao = null;
		} else if (!dataAprovacao.isBefore(this.dataApresentacao)) {
			this.dataAprovacao = dataAprovacao;
		} else {
			throw new IllegalArgumentException("Data de aprovação não pode ser anterior a data de apresentação");
		}
	}

	public String mostrar() {
		String statusAprovacao = (dataAprovacao == null) ? "Não aprovado" : "Aprovado em " + formatter.format(this.dataAprovacao); 
		return "Titulo: " + this.getTitulo() + "\n" +
					 "Número projeto: " + this.getNumeroProjeto() + "\n" +
					 "Data de Apresentação: " + formatter.format(this.getDataApresentacao()) + "\n" +
					  statusAprovacao + "\n";
		
	}
	
}