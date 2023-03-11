import java.util.HashMap;

public class Vereador {

	private String nome;
	private Partido partido;
	
	private HashMap<Integer, ProjetoDeLei> projetosDeLei = new HashMap<>();

	public Vereador(String nome) {
		super();
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null && !nome.isBlank()) {
			this.nome = nome;
		}
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public int getQtdProjApres() {
		return projetosDeLei.size();
	}

	public int getQtdProjAprov() {
	    int somador = 0;
	    for (ProjetoDeLei p : this.projetosDeLei.values()) {
	        if (p.getDataAprovacao() != null) {
	            somador += 1;
	        }
	    }
		return somador;
	}


	public double getDesempenho() {
		if (this.projetosDeLei.size() == 0) {
			return 0;
		}
		return ((double) this.getQtdProjAprov() / (double) this.projetosDeLei.size()) * this.getIndiceTrabalho();
	}

	public double getIndiceTrabalho() {
		if (this.projetosDeLei.size() > 17) {
			return 1.22;
		} else if (this.projetosDeLei.size() > 10) {
			return 1.08;
		} else if (this.projetosDeLei.size() > 5) {
			return 1.0;
		} else if (this.projetosDeLei.size() > 0) {
			return 0.8;
		}
		return 0;
	}

	public void addProjetosDeLei(int IDProjeto, ProjetoDeLei pl) {
		if (!projetosDeLei.containsKey(IDProjeto)) {
			projetosDeLei.put(IDProjeto, pl);
		} else {
			throw new IllegalArgumentException("Numero do projeto já existe.");
		}
	}

	public String mostra() {
		if (projetosDeLei.isEmpty()) {
			return "";
		} else {
			String str = "\n" + this.getNome() + "\nPartido: " + this.getPartido().getNumero() + 
			" - " + this.getPartido().getNome() + "\n";

			for (ProjetoDeLei pl : projetosDeLei.values()) {
				str += pl.mostrar() + "\n";
			}
			return str;
		}
	}

}