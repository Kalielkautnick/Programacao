package pecasXadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro board, Cor color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		// ACIMA		
		p = percorreTabuleiro(mat, posicao, -1, 0);
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversariaNessaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}	
		// ESQUERDA
		p = percorreTabuleiro(mat, posicao, 0, -1);
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversariaNessaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// DIREITA
		p = percorreTabuleiro(mat, posicao, 0, +1);
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversariaNessaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ABAIXO		
		p = percorreTabuleiro(mat, posicao, +1, 0);
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversariaNessaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}

	@Override
	public boolean[][] movimentosRegra(Posicao posicaoRei) {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		int linha = posicao.getLinha();
		int coluna = posicao.getColuna();
		if ((Math.abs(coluna - posicaoRei.getColuna()) <= 1) || (Math.abs(linha - posicaoRei.getLinha()) <= 1)) {
			////////////////////////////////////////////////////////////////////////////
			if (linha < posicaoRei.getLinha()) {				
				p = percorreTabuleiro(mat, posicao, +1, 0);
				if (getTabuleiro().posicaoExiste(p)) {
					if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
						mat[p.getLinha()][p.getColuna()] = true;
						percorreTabuleiro(mat, p, +1, 0); 
					} else if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
			        	}
				} 
			}
			//////////////////////////////////////////////////////////////////////////////////
			else if (linha > posicaoRei.getLinha()) {				
				p = percorreTabuleiro(mat, posicao, -1, 0);
				if (getTabuleiro().posicaoExiste(p)) {
					if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
						mat[p.getLinha()][p.getColuna()] = true;
						percorreTabuleiro(mat, p, -1, 0);				
					} else if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
					}
				}	
			}		 
			//////////////////////////////////////////////////////////////////////////////////
			else if (coluna > posicaoRei.getColuna()) {				
				p = percorreTabuleiro(mat, posicao, 0, -1);
				if (getTabuleiro().posicaoExiste(p)) {
				if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					percorreTabuleiro(mat, p, 0, -1);
				} else if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			} 
		    }
			//////////////////////////////////////////////////////////////////////////////////
			else {				
				p = percorreTabuleiro(mat, posicao, 0, +1);
				if (getTabuleiro().posicaoExiste(p)) {
					if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					percorreTabuleiro(mat, p, 0, +1);
					} else if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
					}
				}
			}
			//////////////////////////////////////////////////////////////////////////////////
			}
		return mat;
	}

	@Override
	public boolean[][] movimentosHipoteticos(Posicao posicaoRei) {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		int linha = posicao.getLinha();
		int coluna = posicao.getColuna();
		int pecasNaFrente = 0;
		if (coluna == posicaoRei.getColuna() || linha == posicaoRei.getLinha()) {
			mat[posicao.getLinha()][posicao.getColuna()] = true;
			////////////////////////////////////////////////////////////////////////////
			if (coluna == posicaoRei.getColuna() && linha < posicaoRei.getLinha()) {
				p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna());
					while (getTabuleiro().posicaoExiste(p) && 
						   ((!getTabuleiro().existeUmaPecaNessaPosicao(p) || pecasNaFrente < 1))) {
			    		mat[p.getLinha()][p.getColuna()] = true;
						if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						pecasNaFrente++;
						}
						p.setPosicoes(p.getLinha() + 1, p.getColuna());
					} 
				if (getTabuleiro().posicaoExiste(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
					}				
			}
			//////////////////////////////////////////////////////////////////////////////////
			else if (coluna == posicaoRei.getColuna() && linha > posicaoRei.getLinha()) {
				p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna());
				while (getTabuleiro().posicaoExiste(p) && 
						   ((!getTabuleiro().existeUmaPecaNessaPosicao(p) || pecasNaFrente < 1))) {
			    		mat[p.getLinha()][p.getColuna()] = true;
						if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						pecasNaFrente++;
						}
						p.setPosicoes(p.getLinha() - 1, p.getColuna());
					} 
				if (getTabuleiro().posicaoExiste(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
					}
			} 
			//////////////////////////////////////////////////////////////////////////////////
			else if (coluna > posicaoRei.getColuna() && linha == posicaoRei.getLinha()) {
				p.setPosicoes(posicao.getLinha(), posicao.getColuna() - 1);
				while (getTabuleiro().posicaoExiste(p) && 
						   ((!getTabuleiro().existeUmaPecaNessaPosicao(p) || pecasNaFrente < 1))) {
			    		mat[p.getLinha()][p.getColuna()] = true;
						if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						pecasNaFrente++;
						}
						p.setPosicoes(p.getLinha(), p.getColuna() - 1);
					} 
				if (getTabuleiro().posicaoExiste(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
					}
			} 
			//////////////////////////////////////////////////////////////////////////////////
			else if (coluna < posicaoRei.getColuna() && linha == posicaoRei.getLinha()) {
				p.setPosicoes(posicao.getLinha(), posicao.getColuna() + 1);
				while (getTabuleiro().posicaoExiste(p) && 
						   ((!getTabuleiro().existeUmaPecaNessaPosicao(p) || pecasNaFrente < 1))) {
			    		mat[p.getLinha()][p.getColuna()] = true;
						if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						pecasNaFrente++;
						}
						p.setPosicoes(p.getLinha(), p.getColuna() + 1);
					} 
				if (getTabuleiro().posicaoExiste(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
					}
			}
			//////////////////////////////////////////////////////////////////////////////////
			}
		return mat;
	}

	public Posicao percorreTabuleiro(boolean[][]matriz, Posicao p, int direcaoLinha, int direcaoColuna) {
		Posicao aux = new Posicao(0, 0);
			aux.setPosicoes(p.getLinha() + direcaoLinha, 
								p.getColuna() + direcaoColuna);
		
		while (getTabuleiro().posicaoExiste(aux) && !getTabuleiro().existeUmaPecaNessaPosicao(aux)) {
			matriz[aux.getLinha()][aux.getColuna()] = true;
			aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
		}
		return aux;
	}

	public Posicao percorreTabuleiroRegra(boolean[][]matriz, Posicao p, int direcaoLinha, int direcaoColuna) {
		Posicao aux = new Posicao(0, 0);
		aux.setPosicoes(p.getLinha() + direcaoLinha, 
								p.getColuna() + direcaoColuna);
		int pecasNaFrente = 0;
		//só pra não chamar o método 2x e poupar mínimo desempenho.
		boolean existePecaNessaPosicao = getTabuleiro().existeUmaPecaNessaPosicao(aux);
		while (getTabuleiro().posicaoExiste(aux) && 
			  ((existePecaNessaPosicao == false || pecasNaFrente < 1))) {
			    	matriz[aux.getLinha()][aux.getColuna()] = true;
						if (existePecaNessaPosicao) {
						    pecasNaFrente++;
						}
						aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
						existePecaNessaPosicao = getTabuleiro().existeUmaPecaNessaPosicao(aux);
					} 
				if (getTabuleiro().posicaoExiste(aux)) {
						matriz[aux.getLinha() + direcaoLinha][aux.getColuna() + direcaoColuna] = true;
				}	
		return aux;
	}
}