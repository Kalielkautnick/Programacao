package pecasXadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Dama extends PecaXadrez {

	public Dama(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "D";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {		
		boolean[][] mat = new boolean[8][8];		
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
		///////////////////////////////////////////////////////////////		
		p = percorreTabuleiro(mat, posicao, -1, 0);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					percorreTabuleiro(mat, p, -1, 0);
				} else {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		/////////////////////////////////////////////////////////////////		
		p = percorreTabuleiro(mat, posicao, 0, -1);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
				    percorreTabuleiro(mat, p, 0, -1);
				} else {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		//////////////////////////////////////////////////////////////////////		
		p = percorreTabuleiro(mat, posicao, 0, +1);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					percorreTabuleiro(mat, p, 0, +1);
				} else {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		////////////////////////////////////////////////////////////		
		p = percorreTabuleiro(mat, posicao, +1, 0);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;					
					percorreTabuleiro(mat, p, +1, 0);
				} else {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		/////////////////////////////////////////////////////////////		
		p = percorreTabuleiro(mat, posicao, -1, -1);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					percorreTabuleiro(mat, p, -1, -1);
				} else {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		/////////////////////////////////////////////////////////////////		
		p = percorreTabuleiro(mat, posicao, -1, +1);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					percorreTabuleiro(mat, p, -1, +1);
				} else {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		////////////////////////////////////////////////////////		
		p = percorreTabuleiro(mat, posicao, +1, +1);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					percorreTabuleiro(mat, p, +1, +1);
				} else {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		///////////////////////////////////////////////////////////		
		p = percorreTabuleiro(mat, posicao, +1, -1);
		if (getTabuleiro().posicaoExiste(p)) {
		if (p.getLinha() == posicaoRei.getLinha() && p.getColuna() == posicaoRei.getColuna()) {
					mat[p.getLinha()][p.getColuna()] = true;
					percorreTabuleiro(mat, p, +1, -1);
				} else {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		/////////////////////////////////////////////////////////////
		return mat;
    }

	@Override
	public boolean[][] movimentosHipoteticos(Posicao posicaoRei) {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		int linha = posicao.getLinha();
		int coluna = posicao.getColuna();			
		mat[posicao.getLinha()][posicao.getColuna()] = true;
		//se cair dentro desse IF, o rei esta numa posicao atingível pela dama
		if (( (coluna + linha) == (posicaoRei.getColuna() + posicaoRei.getLinha()) ) || 
			( Math.abs(linha - coluna) == Math.abs(posicaoRei.getColuna() - posicaoRei.getLinha()) ) || 
			( (coluna == posicaoRei.getColuna()) || (linha == posicaoRei.getLinha()) )) {
		////////////////////////////////////////////////////////////////////////////////////////
		if (coluna == posicaoRei.getColuna() && linha > posicaoRei.getLinha()) {		
		percorreTabuleiroHipotetico(mat, posicao, -1, 0);
		}
		//////////////////////////////////////////////////////////////////////////////////////////
		else if (coluna > posicaoRei.getColuna() && linha == posicaoRei.getLinha()) {		
		percorreTabuleiroHipotetico(mat, posicao, 0, -1); 	
		}		
		/////////////////////////////////////////////////////////////////////////////////////////
		else if (coluna < posicaoRei.getColuna() && linha == posicaoRei.getLinha()) {		
		percorreTabuleiroHipotetico(mat, posicao, 0, +1);			
		}				
		/////////////////////////////////////////////////////////////////////////////////////////
		else if (coluna == posicaoRei.getColuna() && linha < posicaoRei.getLinha()) {				
		percorreTabuleiroHipotetico(mat, posicao, +1, 0);		
		}				
		//////////////////////////////////////////////////////////////////////////////////////
		else if (coluna > posicaoRei.getColuna() && linha > posicaoRei.getLinha()) {					
		percorreTabuleiroHipotetico(mat, posicao, -1, -1);
		}			
		//////////////////////////////////////////////////////////////////////////////	
		else if (coluna < posicaoRei.getColuna() && linha > posicaoRei.getLinha()) {		
		percorreTabuleiroHipotetico(mat, posicao, -1, +1);
		}					
		///////////////////////////////////////////////////////////	
		else if (coluna < posicaoRei.getColuna() && linha < posicaoRei.getLinha()) {				
		percorreTabuleiroHipotetico(mat, posicao, +1, +1);
		}				
		///////////////////////////////////////////////////////////	
		else {				
		percorreTabuleiroHipotetico(mat, posicao, +1, -1);	
		}				
		//////////////////////////////////////////////////////////////
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

	public void percorreTabuleiroHipotetico(boolean[][]matriz, Posicao p, int direcaoLinha, int direcaoColuna) {
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
	}
}