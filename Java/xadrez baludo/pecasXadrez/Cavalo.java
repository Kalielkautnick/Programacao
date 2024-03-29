package pecasXadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "C";
	}

	@Override
	public int getValorMaterial() {
		return 3;
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[8][8];
		
		Posicao p = new Posicao(0, 0);
		
		//AQUI NÃO EXISTE WHILE, A GENTE VALIDA TODAS AS 8 POSIÇÕES EM VOLTA DO CAVALO
		//COMO ELE SE MOVIMENTA EM L, É UMA COMBINAÇÃO DE 1 LINHA E 2 COLUNAS OU 2 LINHAS E 1 COLUNA
		//PARA TODAS AS DIREÇÕES
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setPosicoes(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setPosicoes(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setPosicoes(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setPosicoes(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//RETORNA A MATRIZ DE BOOLEAN
		return mat;
	}

	@Override
	public boolean[][] movimentosRegra(Posicao posicaoRei) {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}

	@Override
	public boolean[][] movimentosHipoteticos(Posicao posicaoRei) {
		return this.movimentosPossiveis();
	}
}