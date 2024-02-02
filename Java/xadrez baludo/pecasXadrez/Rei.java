package pecasXadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {
	
	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public int getValorMaterial() {
		return 0;
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[8][8];
		Posicao p = new Posicao(0, 0);	
		// acima
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// abaixo
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// esquerda
		p.setPosicoes(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// direita
		p.setPosicoes(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// esquerda acima
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// direita acima
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// esquerda abaixo
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}	
		// direita abaixo
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}

	@Override
	public boolean[][] movimentosRegra(Posicao posicaoRei) {
		boolean[][] mat = new boolean[8][8];
		Posicao p = new Posicao(0, 0);
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}	
		return mat;
	}

	@Override
	public boolean[][] movimentosHipoteticos(Posicao posicaoRei) {
		return null;
	}
}