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
	public int getValorMaterial() {
		return 5;
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
		boolean[][] mat = new boolean[8][8];
		int linhaPeca = posicao.getLinha();
		int colunaPeca = posicao.getColuna();
		int linhaRei = posicaoRei.getLinha();
		int colunaRei = posicaoRei.getColuna();
		//faz a peça percorrer no máximo 2 direções...ao invés das 4 posições
		if (linhaPeca > linhaRei) {
			if (Math.abs(colunaRei-colunaPeca) <= 1) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, 0);
			}			
			if (Math.abs(linhaRei-linhaPeca) <= 1) {
				//percorre a direita mesma linha
				if (colunaRei <= colunaPeca) {
					percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, -1);
				} 
				//percorre a direita mesma linha
				if (colunaRei >= colunaPeca) {
					percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, +1);
				} 				
			} 	
		}
		////////////////////////////////////////////////////////
		else if (linhaPeca < linhaRei) {
			if (Math.abs(colunaRei-colunaPeca) <= 1) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, 0);
			}			
			if (Math.abs(linhaRei-linhaPeca) <= 1) {
				//percorre a direita mesma linha
				if (colunaRei <= colunaPeca) {
					percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, -1);
				} 
				//percorre a direita mesma linha
				if (colunaRei >= colunaPeca) {
					percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, +1);
				} 				
			}	
		}
		////////////////////////////////////////////////////////coluna da peca menor que a coluna do rei
		else if (colunaPeca < colunaRei) {
			if (Math.abs(linhaRei-linhaPeca) <= 1) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, +1);
			}			
			if (Math.abs(colunaRei-colunaPeca) <= 1) {
				//percorre a direita mesma linha
				if (linhaRei <= linhaPeca) {
					percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, 0);
				} 
				//percorre a direita mesma linha
				if (colunaRei >= colunaPeca) {
					percorreTabuleiroRegra(mat, posicaoRei, posicao, +1, 0);
				} 				
			}	
		}
		//////////////////////////////////////////////////////// coluna da peca maior que a coluna do rei
		else if (colunaPeca > colunaRei) {
			if (Math.abs(linhaRei-linhaPeca) <= 1) {
				percorreTabuleiroRegra(mat, posicaoRei, posicao, 0, -1);
			}			
			if (Math.abs(colunaRei-colunaPeca) <= 1) {
				//percorre a direita mesma linha
				if (linhaRei <= linhaPeca) {
					percorreTabuleiroRegra(mat, posicaoRei, posicao, -1, 0);
				} 
				//percorre a direita mesma linha
				if (colunaRei >= colunaPeca) {
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
		if (coluna == posicaoRei.getColuna() || linha == posicaoRei.getLinha()) {
			mat[posicao.getLinha()][posicao.getColuna()] = true;			
			if (coluna == posicaoRei.getColuna() && linha < posicaoRei.getLinha()) {				
				percorreTabuleiroHipotetico(mat, posicao, +1, 0);				
			}			
			else if (coluna == posicaoRei.getColuna() && linha > posicaoRei.getLinha()) {				
				percorreTabuleiroHipotetico(mat, posicao, -1, 0);	
			} 			
			else if (coluna > posicaoRei.getColuna() && linha == posicaoRei.getLinha()) {				
				percorreTabuleiroHipotetico(mat, posicao, 0, -1);	
			} 			
			else if (coluna < posicaoRei.getColuna() && linha == posicaoRei.getLinha()) {				
				percorreTabuleiroHipotetico(mat, posicao, 0, +1);	
			}			
			}
		return mat;
	}

	private void percorreTabuleiroRegra(boolean[][]matriz, Posicao posicaoRei, Posicao p, int direcaoLinha, int direcaoColuna) {
		Posicao aux = new Posicao(0, 0);
			aux.setPosicoes(p.getLinha() + direcaoLinha, 
								p.getColuna() + direcaoColuna);
		
		while (getTabuleiro().posicaoExiste(aux) && (getTabuleiro().peca(aux) == null || getTabuleiro().peca(aux).toString().equals("R"))) {
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