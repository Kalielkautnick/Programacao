package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	private Cor cor;
	private int contadorMovimentos;
	protected int valorMaterial;

	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	public abstract int getValorMaterial();
	
	public int getContadorMovimentos() {
		return contadorMovimentos;
	}
	
	protected void incrementeContadorMovimentos() {
		contadorMovimentos++;
	}

	protected void decrementaContadorMovimentos() {
		contadorMovimentos--;
	}

	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.convertePosicaoMatrizEmPosicaoXadrez(posicao);
	}
	
	public boolean existePecaAdversariaNessaPosicao(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
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
}