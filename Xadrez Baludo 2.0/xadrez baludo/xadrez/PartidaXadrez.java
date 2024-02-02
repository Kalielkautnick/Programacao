package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

import pecasXadrez.Bispo;
import pecasXadrez.Cavalo;
import pecasXadrez.Dama;
import pecasXadrez.Peao;
import pecasXadrez.Rei;
import pecasXadrez.Torre;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class PartidaXadrez {

	private int vez;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean cheque;
	private boolean chequeMate;
	private PecaXadrez enPassantPossivel;
	private PecaXadrez promovida;
	
	//NA PARTIDA, TEREMOS AS LISTAS DE PEÇAS QUE ESTÃO EM JOGO, E AS PEÇAS CAPTURADAS
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	private List<String> posicoesAleatorias = new ArrayList<>();
	
	public PartidaXadrez(int opcao) {
		//TODA PARTIDA DE XADREZ COMEÇA COM UM TABULEIRO DE 8X8, NA JOGADA DE NUMERO 1
		//COM AS PEÇAS BRANCAS COMEÇANDO O JOGO
		tabuleiro = new Tabuleiro(8, 8);
		vez = 1;
		jogadorAtual = Cor.BRANCA;
		if (opcao == 1) {
			tabuleiroInicial();
		} else {
			setPosicoesAleatorias();
			tabuleiroInicialAleatorio();
		}				
		chequeMate = reiEstaEmChequeMate(jogadorAtual) || reiEstaEmChequeMate(oponente(jogadorAtual));
		cheque = reiEstaEmCheque(jogadorAtual);
	}

	private void setPosicoesAleatorias () {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				posicoesAleatorias.add(Character.toString((char)('a'+i))+(j+1));
			}
		}
	}
	
	public int getVez() {
		return vez;
	}
	
	//O JOGADOR ATUAL É UMA COR, QUE RETORNA A COR DAS PEÇAS QUE ESTÃO JOGANDO AGORA.
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheque() {
		return cheque;
	}
	
	public boolean getChequeMate() {
		return chequeMate;
	}
	
	public PecaXadrez getEnPassantVulnerable() {
		return enPassantPossivel;
	}
	
	public PecaXadrez getPromovida() {
		return promovida;
	}
	
	//UM DOS MÉTODOS MAIS IMPORTANTES, RETORNA TODAS AS PEÇAS DE XADREZ DENTRO DA MATRIZ DE PEÇAS XADREZ
	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i < tabuleiro.getLinhas(); i++) {
			for (int j=0; j < tabuleiro.getColunas(); j++) {
				//ATRIBUI A MATRIIZ TODAS AS PEÇAS DO TABULEIRO, BUSCANDO NO MÉTODO QUE RETORNA AS PEÇAS, 
				//AS POSIÇÕES DA MATRIZ COMO PARAMETRO DA POSIÇÃO QUE DEVE BUSCAR A PEÇA
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.convertePosicaoXadrezParaPosicaoMatriz();
		validaPosicaoInicial(posicao);
		boolean[][] mat = tabuleiro.peca(posicao).movimentosPossiveis();
		boolean[][] aux = new boolean[8][8];
		Posicao posicaoRei = getRei(jogadorAtual).getPosicaoXadrez().convertePosicaoXadrezParaPosicaoMatriz();
		String pecaSelecionada = tabuleiro.peca(posicao).toString();
		List<Peca> pecasAdversarias = 
			pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() != jogadorAtual && x != null).collect(Collectors.toList()); 
		/*se for o rei, não permite que ele se coloque em cheque, chamando os movimentosRegra de cada uma das peças
		adversárias*/
		if (pecaSelecionada.equals("R")) {
			int linha = posicaoRei.getLinha() == 0 ? 0 : posicaoRei.getLinha() - 1;
			int coluna = posicaoRei.getColuna() == 0 ? 0 : posicaoRei.getColuna() - 1;
                	for (int k = linha; k <= linha+2 && k < 8; k++) {
						for (int m = coluna; m <= coluna+2 && m < 8; m++) {
							for (Peca p : pecasAdversarias) {							
								if (p.movimentosRegra(posicaoRei)[k][m] == true) {
									mat[k][m] = false;
									break;
								}
							}		
						}
				} 
				if (reiEstaImovel(linha, coluna, mat)) {					 
				    throw new ExceptionXadrez("O rei não tem casas disponíveis,  - Press Enter");									
				} 
			return mat;
		} 
		else {
			pecasAdversarias = pecasAdversarias.stream().filter(x -> 
								(x.toString().equals("T") ||
								x.toString().equals("B") ||
								x.toString().equals("D") ||
								x.toString().equals("C") ||
								x.toString().equals("P")) && x != null).collect(Collectors.toList());
			if (getCheque()) {
			//se o rei está em cheque duplo, e a peça selecionada não é o rei, nenhuma jogada é possível. (Já está validado se não é o rei)
		      if (reiEstaEmChequeDuplo(jogadorAtual)) {
			    throw new ExceptionXadrez("Cheque Duplo, apenas o rei pode ser movido - Press Enter");
		      } 
			Peca pecaCheque = getPecaCheque(jogadorAtual);

			//para verificarmos se tem mais alguma peça que esteja ameaçando o rei, temos que tirar 
			//a peça que deu o cheque dessa lista, e verificar as outras.
			pecasAdversarias = pecasAdversarias.stream().filter(x -> 
								(x != pecaCheque) && x != null).collect(Collectors.toList());
			Peca possivelPecaCravada = pecaCravada(posicao, posicaoRei, pecasAdversarias, aux);
			if (possivelPecaCravada != null) {
				Posicao posicaoPecaCravada = possivelPecaCravada.getPosicao(possivelPecaCravada);
				String posicaoXadrez = 
							Character.toString((char)('a' + posicaoPecaCravada.getColuna())) + (8 - posicaoPecaCravada.getLinha()); 
				String s = possivelPecaCravada.toString();
				switch (s) {
					case "D": s = "Dama em ";
					break;
					case "T": s = "Torre em ";
					break;
					case "B": s = "Bispo em ";
				}
				String s2 = pecaSelecionada.toString();
				switch (s2) {
					case "D": s2 = "Dama ";
					break;
					case "T": s2 = "Torre ";
					break;
					case "B": s2 = "Bispo ";
				}
				throw new ExceptionXadrez("Essa peca esta cravada por " + s + posicaoXadrez + 
											". Impossivel mover " + s2 + posicaoOrigem + " - Press Enter");
			} else {
				//se o rei estiver ameaçado apenas pela peça que deu o cheque nele, vamos verificar se a peça selecionada consegue tampar o cheque.
			//o cavalo e o peão são as únicas peças que não dá pra tampar o cheque, ou você mata com a peça selecionada, ou sai com o rei.
			if (pecaCheque.toString().equals("C") || pecaCheque.toString().equals("P")) {
				if (mat[pecaCheque.getPosicao(pecaCheque).getLinha()][pecaCheque.getPosicao(pecaCheque).getColuna()]) {
					mat = new boolean[8][8];
					mat[pecaCheque.getPosicao(pecaCheque).getLinha()][pecaCheque.getPosicao(pecaCheque).getColuna()] = true;
					return mat;
				} else {
					throw new ExceptionXadrez("Essa peca nao consegue defender o cheque - Press Enter");
				}
			}
			else {	//se não for cavalo nem peão que deu cheque, é possível tampar o cheque eventualmente colocando alguma peça na frente.		
			aux = getPecaCheque(jogadorAtual).movimentosHipoteticos(posicaoRei);			    
                	for (int k = 0; k < 8; k++) {
						for (int m = 0; m < 8; m++) {
							if (!aux[k][m] && mat[k][m]) {
								mat[k][m] = false;
							}
						}
				}
				if (matrizTotalmenteFalsa(mat)) {
						throw new ExceptionXadrez("Essa peca nao consegue defender o cheque - Press Enter");
				}
			}
			}
			} else {
				//se não estiver em cheque, vai verificar se a peça está cravada
				Peca p = pecaCravada(posicao, posicaoRei, pecasAdversarias, aux);
					if (p != null) {
						aux = p.movimentosHipoteticos(posicaoRei);
                		aux[posicaoRei.getLinha()][posicaoRei.getColuna()] = false;
						aux[posicao.getLinha()][posicao.getColuna()] = false;
						/*
						for (int k = 0; k < 8; k++) {
							for (int m = 0; m < 8; m++) {
								if (!aux[k][m] && mat[k][m]) {
									mat[k][m] = false;
								}
							}
						}
						return mat;*/
						return aux;						
					}			 
				return mat;
			}
		}
		//nunca vai chegar aqui
		return null;
	}

	private Peca pecaCravada(Posicao posicaoPeca, Posicao posicaoRei, List<Peca> pecasAdversarias, boolean[][] aux) {
		for (Peca p : pecasAdversarias) {
				aux = p.movimentosHipoteticos(posicaoRei);
				if (aux != null) {
				if (aux[posicaoRei.getLinha()][posicaoRei.getColuna()] && 
				    aux[posicaoPeca.getLinha()][posicaoPeca.getColuna()]) {
					return p;
				}
			}
		}
		return null;
	}

	private Peca getPecaCheque(Cor cor) {
		Posicao posicaoRei = getRei(cor).getPosicaoXadrez().convertePosicaoXadrezParaPosicaoMatriz();
		List<Peca> pecasAdversarias = 
		pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() != cor && x != null).collect(Collectors.toList());
		for (Peca p : pecasAdversarias) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return p;
			}
		}
		return null;
	}

	public boolean reiEstaImovel(int linha, int coluna, boolean[][] mat) {
		for (int k = linha; k <= linha+2 && k < 8; k++) {
			for (int m = coluna; m <= coluna+2 && m < 8; m++) {
				if (mat[k][m]) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean matrizTotalmenteFalsa(boolean[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
    		for (int j = 0; j < matriz[i].length; j++) {
        		if (matriz[i][j]) {
            	    return false;
        	    }
    	    }
        }
	   	return true;
	}
	
	private void validaPosicaoInicial(Posicao posicao) {
		if (!tabuleiro.existeUmaPecaNessaPosicao(posicao)) {
			throw new ExceptionXadrez("Nao existe nenhuma peca na posicao escolhida - Press Enter:");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new ExceptionXadrez("A peca que voce escolheu nao e " + getJogadorAtual());
		}
		if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
			throw new ExceptionXadrez("A peca escolhida nao tem movimentos possiveis - Press Enter:");
		}
	}
	
	private void validaPosicaoDestino(Posicao origem, Posicao target) {
		if (!tabuleiro.peca(origem).movimentoPossivel(target)) {
			throw new ExceptionXadrez("A peca escolhida nao pode ir para a posicao selecionada - Press Enter:");
		}
	}

	//MÉTODO CRUCIAL PARA O SISTEMA FUNCIONAR, É ELE QUE VAI MOVIMENTAR AS PEÇAS
	public PecaXadrez movimentarPeca(PosicaoXadrez posicaoInicial, PosicaoXadrez posicaoDestino) {
		Posicao inicial = posicaoInicial.convertePosicaoXadrezParaPosicaoMatriz();
		Posicao destino = posicaoDestino.convertePosicaoXadrezParaPosicaoMatriz();
		validaPosicaoInicial(inicial);
		validaPosicaoDestino(inicial, destino);
		Peca pecaCapturada = makeMove(inicial, destino);
		
		if (reiEstaEmCheque(jogadorAtual)) {
			desfazerMovimento(inicial, destino, pecaCapturada);
			throw new ExceptionXadrez("Voce nao pode deixar seu rei em cheque - Press Enter");
		}
		
		PecaXadrez pecaMovida = (PecaXadrez)tabuleiro.peca(destino);
		
		// #specialmove promotion
		promovida = null;
		if (pecaMovida instanceof Peao) {
			if ((pecaMovida.getCor() == Cor.BRANCA && 
							destino.getLinha() == 0) || 
							(pecaMovida.getCor() == Cor.PRETA && 
							destino.getLinha() == 7)) {
				promovida = (PecaXadrez)tabuleiro.peca(destino);
				promovida = alterarPecaPromovida("D");
			}
		}
		
		//OPERAÇÃO TERNÁRIA, SE O REI DO OPONENTE ESTÁ EM CHEQUE, CHEQUE RECEBE TRUE, SE NÃO, RECEBE FALSO
		cheque = (reiEstaEmCheque(oponente(jogadorAtual))) ? true : false;

		//EM SEGUIDA VALIDA SE O MESMO REI DO OPONENTE ESTÁ EM CHEQUE MATE, AÍ AQUI O SISTEMA VAI ATRIBUIR TRUE
		//AO ATRIBUTO, E NO PROGRAMA PRINCIPAL ELE VAI SAIR FORA DO WHILE (!CHEQUEMATE), E ENCERRAR O JOGO.
		if (reiEstaEmChequeMate(oponente(jogadorAtual))) {
			chequeMate = true;
		} else {
			proximaJogada();
		}
		
		
		
		return (PecaXadrez)pecaCapturada;
	}

	public PecaXadrez alterarPecaPromovida(String tipo) {
		if (promovida == null) {
			throw new IllegalStateException("Nao ha peca para promover");
		}
		if (!tipo.equals("B") && !tipo.equals("T") && !tipo.equals("D") & !tipo.equals("C")) {
			return promovida;
		}
		
		Posicao pos = promovida.getPosicaoXadrez().convertePosicaoXadrezParaPosicaoMatriz();
		Peca p = tabuleiro.removerPeca(pos);
		pecasNoTabuleiro.remove(p);
		
		PecaXadrez pecaNova = newPiece(tipo, promovida.getCor());
		tabuleiro.inserirPeca(pecaNova, pos);
		pecasNoTabuleiro.add(pecaNova);
		
		return pecaNova;
	}
	
	private PecaXadrez newPiece(String type, Cor cor) {
		if (type.equals("B")) return new Bispo(tabuleiro, cor);
		if (type.equals("C")) return new Cavalo(tabuleiro, cor);
		if (type.equals("D")) return new Dama(tabuleiro, cor);
		return new Torre(tabuleiro, cor);
	}
	
	private Peca makeMove(Posicao inicial, Posicao destino) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(inicial);
		p.incrementeContadorMovimentos();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.inserirPeca(p, destino);
		
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	//ESSE MÉTODO DE DESFAZER MOVIMENTO, SERVE PARA VOLTAR UMA PEÇA PARA A POSIÇÃO ANTERIOR, CASO
	//A MOVIMENTAÇÃO DELA TENHA SIDO INVALIDA, QUE TENHA CAUSADO CHEQUE NO PROPRIO REI, ETC...
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez peca = (PecaXadrez)tabuleiro.removerPeca(destino);
		//ELE CHAMA O DECREMENTA CONTADOR DE MOVIMENTOS, PORQUE SE POR VENTURA MOVIMENTAR UM PEÃO E VOLTAR
		//O CONTADOR DE MOVIMENTOS DELE FICARIA COM 1, E NUMA PROXIMA JOGADA ELE NÃO PODERIA ANDAR DUAS
		//CASAS PRA FRENTE, E EM SEGUIDA ELE INSERE A PEÇA MOVIMENTADA PARA A POSIÇÃO DE ORIGEM QUE ELA SAIU
		peca.decrementaContadorMovimentos();
		tabuleiro.inserirPeca(peca, origem);
		
		//SE ALGUMA PEÇA FOI CAPTURADA NESSE MOVIMENTO, MAS O MOVIMENTO TEVE QUE SER VOLTADO, ELE INSERE A PEÇA CAPTURADA
		//NA POSIÇÃO QUE ELA FOI REMOVIDA, E ELE REMOVE ESSA PEÇA DA LISTA DE CAPTURADAS, E ADICIONA NOVAMENTE ESSA 
		//PECA RECUPERADA PARA A LISTA DE PEÇAS NO TABULEIRO.
		if (pecaCapturada != null) {
			tabuleiro.inserirPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}

	}
		
	private void proximaJogada() {
		vez++;
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private PecaXadrez getRei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor && x != null).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("O rei da cor" + cor + " nao esta no tabuleiro");
	}

	private boolean reiEstaEmChequeDuplo(Cor cor) {
		Posicao posicaoRei = getRei(cor).getPosicaoXadrez().convertePosicaoXadrezParaPosicaoMatriz();
		List<Peca> pecasAdversarias = 
		pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() != cor && x != null).collect(Collectors.toList());
		int contadorCheques = 0;
		for (Peca p : pecasAdversarias) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				contadorCheques++;
			}
			if (contadorCheques == 2) {
				return true;
			}
		}
		return contadorCheques >= 2;
	}
	
	private boolean reiEstaEmCheque(Cor cor) {
		Posicao posicaoRei = getRei(cor).getPosicaoXadrez().convertePosicaoXadrezParaPosicaoMatriz();
		List<Peca> pecasAdversarias = 
	pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() != cor && x != null).collect(Collectors.toList());
		for (Peca p : pecasAdversarias) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean reiEstaEmChequeMate(Cor cor) {
		/*
		if (!reiEstaEmCheque(cor)) {
			return false;
		}
		*/
		List<Peca> listaAux = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor && x != null).collect(Collectors.toList());
		for (Peca p : listaAux) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().convertePosicaoXadrezParaPosicaoMatriz();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = makeMove(origem, destino);
						boolean reiEstaEmCheque = reiEstaEmCheque(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (reiEstaEmCheque == false) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}	
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.inserirPeca(peca, new PosicaoXadrez(coluna, linha).convertePosicaoXadrezParaPosicaoMatriz());
		pecasNoTabuleiro.add(peca);
	}

	private String geradorPosicaoXadrezAleatoria() {
		String pos = posicoesAleatorias.get(new Random().nextInt(posicoesAleatorias.size()));
		posicoesAleatorias.remove(pos);
		return pos;
	}
	
	private void tabuleiroInicial() {
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));			
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));		
        colocarNovaPeca('d', 1, new Dama(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA, this));
		
        colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		////////////////////////////////
        colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETA));
        colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
        colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETA));
        colocarNovaPeca('d', 8, new Dama(tabuleiro, Cor.PRETA));
        colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA, this));

        colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETA, this));
	}

	//ESSE É O MÉTODO ONDE COLOCA TODAS AS PEÇAS NAS POSIÇÕES INICIAIS DELA, MAS, O DESENVOLVEDOR PODE
	//ALTERAR A POSIÇÃO INICIAL, SE QUISER JOGAR DE UM JEITO DIFERENTE
	private void tabuleiroInicialAleatorio() {
		String [] posAleatorias = new String[32];
		char [] posColuna = new char[32];
		int [] posLinha = new int[32];

		for (int i = 0; i < posAleatorias.length; i++) {
			posAleatorias[i] = geradorPosicaoXadrezAleatoria();
			posColuna[i] = posAleatorias[i].charAt(0);
			posLinha[i] = Integer.parseInt(posAleatorias[i].substring(1,2));
		}		
		 /* 
		colocarNovaPeca(posColuna[0], posLinha[0], new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeca(posColuna[1], posLinha[1], new Torre(tabuleiro, Cor.BRANCA));
        colocarNovaPeca(posColuna[2], posLinha[2], new Cavalo(tabuleiro, Cor.BRANCA));
		colocarNovaPeca(posColuna[3], posLinha[3], new Cavalo(tabuleiro, Cor.BRANCA));			
		colocarNovaPeca(posColuna[4], posLinha[4], new Bispo(tabuleiro, Cor.BRANCA));
		colocarNovaPeca(posColuna[5], posLinha[5], new Bispo(tabuleiro, Cor.BRANCA));		
        colocarNovaPeca(posColuna[6], posLinha[6], new Dama(tabuleiro, Cor.BRANCA));
        colocarNovaPeca(posColuna[7], posLinha[7], new Rei(tabuleiro, Cor.BRANCA, this));
		*/
		/*
        colocarNovaPeca(posColuna[8], posLinha[8], new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca(posColuna[9], posLinha[9], new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca(posColuna[10], posLinha[10], new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca(posColuna[11], posLinha[11], new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca(posColuna[12], posLinha[12], new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca(posColuna[13], posLinha[13], new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca(posColuna[14], posLinha[14], new Peao(tabuleiro, Cor.BRANCA, this));
        colocarNovaPeca(posColuna[15], posLinha[15], new Peao(tabuleiro, Cor.BRANCA, this));
		*/
		////////////////////////////////
		/*
        colocarNovaPeca(posColuna[16], posLinha[16], new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeca(posColuna[17], posLinha[17], new Torre(tabuleiro, Cor.PRETA));
        colocarNovaPeca(posColuna[18], posLinha[18], new Cavalo(tabuleiro, Cor.PRETA));
		colocarNovaPeca(posColuna[19], posLinha[19], new Cavalo(tabuleiro, Cor.PRETA));
        colocarNovaPeca(posColuna[20], posLinha[20], new Bispo(tabuleiro, Cor.PRETA));
		colocarNovaPeca(posColuna[21], posLinha[21], new Bispo(tabuleiro, Cor.PRETA));
        colocarNovaPeca(posColuna[22], posLinha[22], new Dama(tabuleiro, Cor.PRETA));
        colocarNovaPeca(posColuna[23], posLinha[23], new Rei(tabuleiro, Cor.PRETA, this));
		*/
		
	/* 
        colocarNovaPeca(posColuna[24], posLinha[24], new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca(posColuna[25], posLinha[25], new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca(posColuna[26], posLinha[26], new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca(posColuna[27], posLinha[27], new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca(posColuna[28], posLinha[28], new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca(posColuna[29], posLinha[29], new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca(posColuna[30], posLinha[30], new Peao(tabuleiro, Cor.PRETA, this));
        colocarNovaPeca(posColuna[31], posLinha[31], new Peao(tabuleiro, Cor.PRETA, this));
		*/


		colocarNovaPeca('d', 5, new Bispo(tabuleiro, Cor.BRANCA));		

        colocarNovaPeca('d', 7, new Rei(tabuleiro, Cor.BRANCA, this));

		colocarNovaPeca('d', 4, new Torre(tabuleiro, Cor.PRETA));

		colocarNovaPeca('d', 8, new Cavalo(tabuleiro, Cor.PRETA));

        colocarNovaPeca('c', 3, new Rei(tabuleiro, Cor.PRETA, this));
	}
}