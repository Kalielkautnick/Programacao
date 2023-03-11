import java.util.ArrayList;

public class Partido {
	private String numero;
	private String nome;
	private ArrayList<Vereador> vereadores = new ArrayList<>();

	Partido() {
	}

	public void addVereador(Vereador vereador) {
		this.vereadores.add(vereador);
		vereador.setPartido(this);
	}

	public int getTotalProjetosApresentados() {
		int total = 0;
		for (Vereador v : this.vereadores) {
			total += v.getQtdProjApres();
		}
		return total;
	}

	public int getTotalProjetosAprovados() {
		int total = 0;
		for (Vereador v : this.vereadores) {
			total += v.getQtdProjAprov();
		}
		return total;
	}

	public double getMediaDesempenho() {
		double media = 0.0;
		for (Vereador v : this.vereadores) {
			media += v.getDesempenho();
		}
		return media / this.vereadores.size();
	}

	public Vereador getVereadorMenorDesempenho() {
		if (this.vereadores.isEmpty()) {
			return null;
		}
		Vereador piorVereador = this.vereadores.get(0);
		double menorDesempenho = piorVereador.getDesempenho();
		for (Vereador v : this.vereadores) {
			if (v.getDesempenho() < menorDesempenho) {
				piorVereador = v;
				menorDesempenho = v.getDesempenho();
			}
		}
		return piorVereador;
	}

	public int getTotalVereadores() {
		return this.vereadores.size();
	}

	public ArrayList<Vereador> getVereadores() {
		return this.vereadores;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		String aux = numero.replaceAll("[\\D]", "");
		if (aux.length() > 0) {
			this.numero = aux;
		} else {
			throw new IllegalArgumentException("Número do partido precisa conter números");
		}
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Vereador> getVereadoresAcima(double linhaDeCorte) {
		ArrayList<Vereador> vereadoresAcima = new ArrayList<>();

		for (Vereador v : vereadores) {
			if (v.getDesempenho() > linhaDeCorte) {
				vereadoresAcima.add(v);
			}
		}
		return vereadoresAcima;
	}

	public void addProjetoVereador(String numVereador, ProjetoDeLei pl) {
		for (Vereador v : vereadores) {

			if (v.getNome().equals(numVereador)) {
				v.addProjetosDeLei(pl.getNumeroProjeto(), pl);
				return;
			}
		}
		
		throw new IllegalArgumentException("Vereador não cadastrado");
	}

	public String mostra() {

		String str = "";

		for (Vereador v : vereadores) {
			str += v.mostra();
		}

		return str;
	}

}