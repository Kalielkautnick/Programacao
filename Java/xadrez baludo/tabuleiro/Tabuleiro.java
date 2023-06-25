package tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int qtdeLinhas, int qtdeColunas) {
		if (qtdeLinhas < 1 || qtdeColunas < 1) {
			throw new ExceptionTabuleiro("Erro ao criar tabuleiro: o minimo de colunas ou linhas e 1");
		}
		this.linhas = qtdeLinhas;
		this.colunas = qtdeColunas;
		pecas = new Peca[qtdeLinhas][qtdeColunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca peca(int linha, int coluna) {
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
			throw new ExceptionTabuleiro("A posicao escolhida nao existe no tabuleiro - Press enter:");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao position) {
		if (!posicaoExiste(position)) {
			throw new ExceptionTabuleiro("Posicao nao existe no tabuleiro - Press enter");
		}
		return pecas[position.getLinha()][position.getColuna()];
	}
	
	public void inserirPeca(Peca peca, Posicao posicao) {
		if (existeUmaPecaNessaPosicao(posicao)) {
			throw new ExceptionTabuleiro("Ja existe uma peca da sua cor na posicao: " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removerPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new ExceptionTabuleiro("A posicao escolhida nao existe no tabuleiro - Press Enter");
		}
		if (peca(posicao) == null) {
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	
	public boolean posicaoExiste(Posicao posicao) {
		int linha = posicao.getLinha();
		int coluna = posicao.getColuna();
		return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
	}
	
	public boolean existeUmaPecaNessaPosicao(Posicao position) {
		if (!posicaoExiste(position)) {
			throw new ExceptionTabuleiro("A posicao escolhida nao existe no tabuleiro - Press Enter");
		}
		return peca(position) != null;
	}
}