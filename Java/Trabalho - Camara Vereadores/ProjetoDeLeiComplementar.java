import java.time.LocalDate;
import java.util.InputMismatchException;

public class ProjetoDeLeiComplementar extends ProjetoDeLei {
	
	private String artigoLO;
	private int qtdVotosFavoraveis;

	
	public ProjetoDeLeiComplementar(Integer numero, String titulo, LocalDate dataApresentacao, LocalDate dataAprovacao,
								   String artigoLO, int qtdVotosFavoraveis) {
		
		super(numero, titulo, dataApresentacao, dataAprovacao);
		this.setArtigoLO(artigoLO);
		this.setQtdVotosFavoraveis(qtdVotosFavoraveis);
	}


	public String getArtigoLO() {
		return artigoLO;
	}


	public void setArtigoLO(String artigoLO) {
		if (artigoLO != null && !artigoLO.isBlank()) {
			this.artigoLO = artigoLO;
		} else {
			throw new NullPointerException("Artigo nao preenchido.");
		}
	}

	public int getQtdVotosFavoraveis() {
		return qtdVotosFavoraveis;
	}


	public void setQtdVotosFavoraveis(int qtdVotosFavoraveis) {
		if(qtdVotosFavoraveis >= 0) {
			this.qtdVotosFavoraveis = qtdVotosFavoraveis;
		} else if (qtdVotosFavoraveis < 0) {
			throw new IllegalArgumentException("Quantidade de votos inválido");
		} else {
			throw new InputMismatchException("Quantidade de votos precisa ser numerica");
		}
	}


	@Override
	public String mostrar() {
		String statusAprovacao = (this.getDataAprovacao() == null) ? "Não aprovado" : "Aprovado em " + formatter.format(this.getDataAprovacao()); 
		return "Titulo: " + this.getTitulo() + "\n" +
					 "Número projeto: " + this.getNumeroProjeto() + "\n" +
					 "Artigo da LO: " + this.getArtigoLO() + "\n" +
					 "Quantidade Votos Favoraveis: " + this.getQtdVotosFavoraveis() + "\n" +
					 "Data de Apresentação: " + formatter.format(this.getDataApresentacao()) + "\n" +
					  statusAprovacao + "\n";
	}
}