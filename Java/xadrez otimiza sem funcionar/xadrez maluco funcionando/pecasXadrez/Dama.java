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
		boolean[][] mat = new boolean[8][8];
		int linhaPeca = posicao.getLinha();
		int colunaPeca = posicao.getColuna();
		int linhaRei = posicaoRei.getLinha();
		int colunaRei = posicaoRei.getColuna();
		////////////////////////////////////////////////////////
		//faz a peça percorrer no máximo 5 direções...ao invés das 8 posições
		if (linhaPeca > linhaRei) {
			if (colunaRei==colunaPeca && Math.abs(linhaRei-linhaPeca) > 2) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, 0);
				return mat;
			}
			//percorre pra cima
			if (Math.abs(colunaRei-colunaPeca) <= 1) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, 0);
			}
			//percorre diagonal esquerda acima
			if (colunaRei <= colunaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, -1);
				//percorre na mesma linha a esquerda
					if (Math.abs(linhaRei-linhaPeca) <= 1) {
						percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, -1);
					}
			} 
			//percorre diagonal direita acima
			if (colunaRei >= colunaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, +1);
				//percorre na mesma linha a direita
					if (Math.abs(linhaRei-linhaPeca) <= 1) {
						percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, +1);
					}
			}	
		}
		////////////////////////////////////////////////////////
		else if (linhaPeca < linhaRei) {
			if (colunaRei==colunaPeca && Math.abs(linhaRei-linhaPeca) > 2) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, 0);
				return mat;
			}
			//percorre pra baixo
			if (Math.abs(colunaRei-colunaPeca) <= 1) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, 0);
			}
			//percorre diagonal esquerda abaixo
			if (colunaRei <= colunaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, -1);
				//percorre na mesma linha a esquerda
					if (Math.abs(linhaRei-linhaPeca) <= 1) {
						percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, -1);
					}
			} 
			//percorre diagonal direita abaixo
			if (colunaRei >= colunaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, +1);
				//percorre na mesma linha a direita
					if (Math.abs(linhaRei-linhaPeca) <= 1) {
						percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, +1);
					}
			}	
		}
		////////////////////////////////////////////////////////coluna da peca menor que a coluna do rei
		else if (colunaPeca < colunaRei) {
			if (linhaRei==linhaPeca && Math.abs(colunaRei-colunaPeca) > 2) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, +1);
				return mat;
			}
			//percorre pra direita
			if (Math.abs(linhaRei-linhaPeca) <= 1) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, +1);
			}
			//percorre diagonal direita acima
			if (linhaRei <= linhaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, +1);
				//percorre na mesma coluna a esquerda
					if (Math.abs(colunaRei-colunaPeca) <= 1) {
						percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, 0);
					}
			} 
			//percorre diagonal direita abaixo
			if (linhaRei >= linhaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, +1);
				//percorre na mesma coluna a direita
					if (Math.abs(colunaRei-colunaPeca) <= 1) {
						percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, 0);
					}
			}	
		}
		//////////////////////////////////////////////////////// coluna da peca maior que a coluna do rei
		else if (colunaPeca > colunaRei) {
			if (linhaRei==linhaPeca && Math.abs(colunaRei-colunaPeca) > 2) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, -1);
				return mat;
			}
			//percorre pra esquerda
			if (Math.abs(linhaRei-linhaPeca) <= 1) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, -1);
			}
			//percorre diagonal esquerda acima
			if (linhaRei <= linhaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, -1);
				//percorre na mesma coluna a esquerda
					if (Math.abs(colunaRei-colunaPeca) <= 1) {
						percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, 0);
					}
			} 
			//percorre diagonal esquerda abaixo
			if (linhaRei >= linhaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, -1);
				//percorre na mesma coluna a direita
					if (Math.abs(colunaRei-colunaPeca) <= 1) {
						percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, 0);
					}
			}	
		}
		return mat;
    }

	@Override
	public boolean[][] movimentosHipoteticos(Posicao posicaoRei) {
		boolean[][] mat = new boolean[8][8];		
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
		
		while (getTabuleiro().posicaoExiste(aux) && getTabuleiro().peca(aux) == null) {
			matriz[aux.getLinha()][aux.getColuna()] = true;
			aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
		}
		return aux;
	}

	public Posicao percorreTabuleiroRegra(boolean[][]matriz, Posicao posicaoRei, Posicao p, int direcaoLinha, int direcaoColuna) {
		Posicao aux = new Posicao(0, 0);
			aux.setPosicoes(p.getLinha() + direcaoLinha, 
								p.getColuna() + direcaoColuna);
		
		while (getTabuleiro().posicaoExiste(aux) && getTabuleiro().peca(aux) == null) {
			matriz[aux.getLinha()][aux.getColuna()] = true;
			aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
		}
		if (getTabuleiro().posicaoExiste(p)) {
		if (aux.getLinha() == posicaoRei.getLinha() && aux.getColuna() == posicaoRei.getColuna()) {
					matriz[aux.getLinha()][aux.getColuna()] = true;
					aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
					while (getTabuleiro().posicaoExiste(aux) && getTabuleiro().peca(aux) == null) {
						matriz[aux.getLinha()][aux.getColuna()] = true;
						aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
					}
				} else {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
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
		while (getTabuleiro().posicaoExiste(aux) && ((existePecaNessaPosicao == false || pecasNaFrente < 1))) {
			    	matriz[aux.getLinha()][aux.getColuna()] = true;
						if (existePecaNessaPosicao) {
						    pecasNaFrente++;
						}
						aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
						if (getTabuleiro().posicaoExiste(aux)) {
						existePecaNessaPosicao = getTabuleiro().existeUmaPecaNessaPosicao(aux);
						}
					} 
				if (getTabuleiro().posicaoExiste(aux)) {
						matriz[aux.getLinha()][aux.getColuna()] = true;
				}			
	}
}