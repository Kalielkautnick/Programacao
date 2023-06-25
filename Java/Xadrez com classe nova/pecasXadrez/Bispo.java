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
		boolean[][] mat = new boolean[8][8];
		
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
		boolean[][] mat = new boolean[8][8];	
        int linhaPeca = posicao.getLinha();
		int colunaPeca = posicao.getColuna();
		int linhaRei = posicaoRei.getLinha();
		int colunaRei = posicaoRei.getColuna();
		////////////////////////////////////////////////////////
		//faz a peça percorrer no máximo 2 direções...ao invés das 4 posições
		if (linhaPeca > linhaRei) {
			if (colunaRei==colunaPeca && Math.abs(linhaRei-linhaPeca) > 2) {
				return mat;
			}
			//percorre diagonal esquerda acima
			if (colunaRei <= colunaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, -1);
			} 
			//percorre diagonal direita acima
			if (colunaRei >= colunaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, +1);
			}	
		}
		////////////////////////////////////////////////////////
		else if (linhaPeca < linhaRei) {
			if (colunaRei==colunaPeca && Math.abs(linhaRei-linhaPeca) > 2) {
				return mat;
			}
			//percorre diagonal esquerda abaixo
			if (colunaRei <= colunaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, -1);
			} 
			//percorre diagonal direita abaixo
			if (colunaRei >= colunaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, +1);
			}	
		}
		////////////////////////////////////////////////////////coluna da peca menor que a coluna do rei
		else if (colunaPeca < colunaRei) {
			if (linhaRei==linhaPeca && Math.abs(colunaRei-colunaPeca) > 2) {
				return mat;
			}
			//percorre diagonal direita acima
			if (linhaRei <= linhaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, +1);
			} 
			//percorre diagonal direita abaixo
			if (linhaRei >= linhaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, +1);
			}	
		}
		//////////////////////////////////////////////////////// coluna da peca maior que a coluna do rei
		else if (colunaPeca > colunaRei) {
			if (linhaRei==linhaPeca && Math.abs(colunaRei-colunaPeca) > 2) {
				return mat;
			}
			//percorre diagonal esquerda acima
			if (linhaRei <= linhaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, -1);
			} 
			//percorre diagonal esquerda abaixo
			if (linhaRei >= linhaPeca) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, -1);
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
		//se cair dentro desse IF, o rei esta numa posicao atingível pelo bispo
		if ((coluna + linha == posicaoRei.getColuna() + posicaoRei.getLinha()) || 
			Math.abs(linha - coluna) == Math.abs(posicaoRei.getColuna() - posicaoRei.getLinha())) {		
		if (coluna > posicaoRei.getColuna() && linha > posicaoRei.getLinha()) {		
		percorreTabuleiroHipotetico(mat, posicao, -1, -1);	
		}		
		else if (coluna < posicaoRei.getColuna() && linha > posicaoRei.getLinha()) {		
		percorreTabuleiroHipotetico(mat, posicao, -1, +1);	
	    }		
		else if (coluna < posicaoRei.getColuna() && linha < posicaoRei.getLinha()) {		
		percorreTabuleiroHipotetico(mat, posicao, +1, +1);	
		}		
		else {		
		percorreTabuleiroHipotetico(mat, posicao, +1, -1);	
		}
		}	
		return mat;
	}
	
	private Posicao percorreTabuleiro(boolean[][]matriz, Posicao p, int direcaoLinha, int direcaoColuna) {
		Posicao aux = new Posicao(0, 0);
			aux.setPosicoes(p.getLinha() + direcaoLinha, 
								p.getColuna() + direcaoColuna);
		
		while (getTabuleiro().posicaoExiste(aux) && !getTabuleiro().existeUmaPecaNessaPosicao(aux)) {
			matriz[aux.getLinha()][aux.getColuna()] = true;
			aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
		}
		return aux;
	}

	private void percorreTabuleiroRegra(boolean[][]matriz, Posicao posicaoRei, Posicao p, int direcaoLinha, int direcaoColuna) {
		Posicao aux = new Posicao(0, 0);
			aux.setPosicoes(p.getLinha() + direcaoLinha, 
								p.getColuna() + direcaoColuna);
								
		while (getTabuleiro().posicaoExiste(aux) && getTabuleiro().peca(aux) == null) {
			matriz[aux.getLinha()][aux.getColuna()] = true;
			aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
		}
		if (getTabuleiro().posicaoExiste(aux)) {
		if (aux.getLinha() == posicaoRei.getLinha() && aux.getColuna() == posicaoRei.getColuna()) {
					matriz[aux.getLinha()][aux.getColuna()] = true;
					aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
					while (getTabuleiro().posicaoExiste(aux) && getTabuleiro().peca(aux) == null) {
						matriz[aux.getLinha()][aux.getColuna()] = true;
						aux.setPosicoes(aux.getLinha() + direcaoLinha, aux.getColuna() + direcaoColuna);
					}
				} else {
					matriz[aux.getLinha()][aux.getColuna()] = true;
				}
			}
	}

    private void percorreTabuleiroHipotetico(boolean[][]matriz, Posicao p, int direcaoLinha, int direcaoColuna) {
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