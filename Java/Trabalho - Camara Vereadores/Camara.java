import java.util.ArrayList;

public class Camara {
	private ArrayList<Partido> partidos = new ArrayList<>();
	int contadorProjetos = 1;

	public Camara() {
	}
	
	public int getContProjetos() {
		return this.contadorProjetos;
	}

	public void addPartido(Partido partido) {
		if (partido != null) {
			this.partidos.add(partido);
		} else {
			throw new NullPointerException("O Partido não contém todas as informações necessárias.");
		}
	}

	public Partido getPartido(String numero) {
		for (Partido p : partidos) {
			if (p.getNumero().equals(numero)) {
				return p;
			}
		}
		return null;
	}

	public void addVereador(Vereador vereador, Partido partido) {
		if (vereador != null && partido != null) {
			partido.addVereador(vereador);
		} else {
		throw new NullPointerException("O Vereador não contém todas as informações necessárias.");
		}
	}

	public Vereador getVereadorMenorDesempenho() {
		double menorDesempenho = Double.MAX_VALUE;
		Vereador vereadorMenorDesempenho = null;

		for (Partido partido : this.partidos) {
			Vereador vereadorMenorDesempenhoPartido = partido.getVereadorMenorDesempenho();
			if (vereadorMenorDesempenhoPartido != null
					&& vereadorMenorDesempenhoPartido.getDesempenho() < menorDesempenho) {
				menorDesempenho = vereadorMenorDesempenhoPartido.getDesempenho();
				vereadorMenorDesempenho = vereadorMenorDesempenhoPartido;
			}
		}
		return vereadorMenorDesempenho;
	}

	
	public int getTotalProjApres() {
		int totalProjApres = 0;

		for (Partido partido : this.partidos) {
			totalProjApres += partido.getTotalProjetosApresentados();
		}
		return totalProjApres;
	}

	
	public int getTotalProjAprov() {
		int totalProjAprov = 0;

		for (Partido partido : this.partidos) {
			totalProjAprov += partido.getTotalProjetosAprovados();
		}
		return totalProjAprov;
	}

	public Vereador getVereadorMaisProjAprov() {
		int projetosAprov = 0;
		Vereador vereadorMaisProjAprov = null;

		for (Partido partido : this.partidos) {
			ArrayList<Vereador> lista = partido.getVereadores();
			for (Vereador v : lista) {
				if (v.getQtdProjAprov() > projetosAprov) {
					projetosAprov = v.getQtdProjAprov();
					vereadorMaisProjAprov = v;
				}
			}
		}
		return vereadorMaisProjAprov;
	}

	public int getQtdPartidos() {
		return this.partidos.size();
	}

	public double getDesempenhoMedioCamara() {
		double somaMediaPartidos = 0;
		int conta = 0;

		if (partidos.size() == 0) {
			return 0;
		}

		for (Partido partido : this.partidos) {
			somaMediaPartidos += partido.getMediaDesempenho() * partido.getTotalVereadores();
			conta += partido.getTotalVereadores();
		}
		return somaMediaPartidos / conta;
	}

	public ArrayList<Vereador> getVereadoresAcimaMedia() {
		ArrayList<Vereador> vereadoresAcimaMediaCamara = new ArrayList<>();
		double mediaCamara = this.getDesempenhoMedioCamara();

		for (Partido partido : this.partidos) {
			ArrayList<Vereador> vereadoresAcimaMediaPartido = partido.getVereadoresAcima(mediaCamara);
			vereadoresAcimaMediaCamara.addAll(vereadoresAcimaMediaPartido);
		}
		return vereadoresAcimaMediaCamara;
	}

	public int gerarNumeroProjeto() {
		contadorProjetos++;
		return contadorProjetos;
	}
	
	public void cancelarNumeroProjeto() {
		contadorProjetos--;
	}
	
	public void addProjeto(String numPartido, String nmVereador, ProjetoDeLei pl) {
		
		for (Partido p : partidos) {
			
			if(p.getNumero().equals(numPartido)) {
				p.addProjetoVereador(nmVereador, pl);
				return;
			} 
		}
		
		throw new IllegalArgumentException("Patido não encontrado");
	}
	
	public String mostra() {
		 
		String str="";
		
		for (Partido p : partidos) {
			str += p.mostra();
		}
		
		return str;
	}
}