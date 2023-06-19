package tabuleiro;

public abstract class Peca {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	public Posicao getPosicao(Peca p) {
		return this.posicao;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean[][] movimentosPossiveis();
	public abstract boolean[][] movimentosRegra(Posicao posicaoRei);
	public abstract boolean[][] movimentosHipoteticos(Posicao posicaoRei);
	
	public boolean movimentoPossivel(Posicao posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}

	public boolean movimentoHipotetico(Posicao posicao) {
		return movimentosHipoteticos(posicao)[posicao.getLinha()][posicao.getColuna()];
	}

	public boolean movimentoRegra(Posicao posicao) {
		return movimentosRegra(posicao)[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean existeAlgumMovimentoPossivel() {
		boolean[][] mat = movimentosPossiveis();
		for (int i=0; i< mat.length; i++) {
			for (int j=0; j< mat.length; j++) {
				if (mat[i][j] == true) {
					return true;
				}
			}
		}
		return false;
	}
}