package pecasXadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
// DIAGONAL ESQUERDA ACIMA
		p = percorreTabuleiro(mat, posicao, -1, -1);
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversariaNessaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// DIAGONAL DIREITA ACIMA		
		p = percorreTabuleiro(mat, posicao, -1, +1);
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversariaNessaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// DIAGONAL DIREITA ABAIXO		
		p = percorreTabuleiro(mat, posicao, +1, +1);
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversariaNessaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// DIAGONAL ESQUERDA ABAIXO
		p = percorreTabuleiro(mat, posicao, +1, -1);
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversariaNessaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}		
		return mat;
	}
   
	@Override
	public boolean[][] movimentosRegra(Posicao posicaoRei) {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);		
		p = percorreTabuleiro(mat, posicao, -1, -1);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					p = percorreTabuleiro(mat, p, -1, -1);
				} else if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}		
		p = percorreTabuleiro(mat, posicao, -1, +1);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					percorreTabuleiro(mat, p, -1, +1);
				} else if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		}		
		p = percorreTabuleiro(mat, posicao, +1, +1);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					p = percorreTabuleiro(mat, p, +1, +1);
				}  else if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}		
		p = percorreTabuleiro(mat, posicao, +1, -1);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					p = percorreTabuleiro(mat, p, +1, -1);
				} else if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
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
		mat[posicao.getLinha()][posicao.getColuna()] = true;
		//se cair dentro desse IF, o rei esta numa posicao atingível pelo bispo
		if ((coluna + linha == posicaoRei.getColuna() + posicaoRei.getLinha()) || 
			Math.abs(linha - coluna) == Math.abs(posicaoRei.getColuna() - posicaoRei.getLinha())) {
		//////////////////////////////////////////////////////////////////////////////////////////
		if (coluna > posicaoRei.getColuna() && linha > posicaoRei.getLinha()) {
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna() - 1);
						while (getTabuleiro().posicaoExiste(p) && 
						   ((!getTabuleiro().existeUmaPecaNessaPosicao(p) || pecasNaFrente < 1))) {
			    		mat[p.getLinha()][p.getColuna()] = true;
						if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						pecasNaFrente++;
						}
						p.setPosicoes(p.getLinha() - 1, p.getColuna() - 1);
					} 
				if (getTabuleiro().posicaoExiste(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
					}
		}
		//////////////////////////////////////////////////////////////////////////////////////////
		else if (coluna < posicaoRei.getColuna() && linha > posicaoRei.getLinha()) {
		p.setPosicoes(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && 
						   ((!getTabuleiro().existeUmaPecaNessaPosicao(p) || pecasNaFrente < 1))) {
			    		mat[p.getLinha()][p.getColuna()] = true;
						if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						pecasNaFrente++;
						}
						p.setPosicoes(p.getLinha() - 1, p.getColuna() + 1);
					} 
				if (getTabuleiro().posicaoExiste(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
					}
	}
		///////////////////////////////////////////////////////////////////////////////
		else if (coluna < posicaoRei.getColuna() && linha < posicaoRei.getLinha()) {
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && 
						   ((!getTabuleiro().existeUmaPecaNessaPosicao(p) || pecasNaFrente < 1))) {
			    		mat[p.getLinha()][p.getColuna()] = true;
						if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						pecasNaFrente++;
						}
						p.setPosicoes(p.getLinha() + 1, p.getColuna() + 1);
					} 
				if (getTabuleiro().posicaoExiste(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
					}
		}
		/////////////////////////////////////////////////////////////////////////////
		else {
		p.setPosicoes(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && 
						   ((!getTabuleiro().existeUmaPecaNessaPosicao(p) || pecasNaFrente < 1))) {
			    		mat[p.getLinha()][p.getColuna()] = true;
						if (getTabuleiro().existeUmaPecaNessaPosicao(p)) {
						pecasNaFrente++;
						}
						p.setPosicoes(p.getLinha() + 1, p.getColuna() - 1);
					} 
				if (getTabuleiro().posicaoExiste(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
					}
		}
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