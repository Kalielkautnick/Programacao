package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;


public class Interface {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String preto = "\u001B[30m";
	public static final String vermelho = "\u001B[31m";
	public static final String verde = "\u001B[32m";
	public static final String amarelo = "\u001B[33m";
	public static final String azul = "\u001B[34m";
	public static final String roxo = "\u001B[35m";
	public static final String ciano = "\u001B[36m";
	public static final String branco = "\u001B[37m";

	public static final String fundoPreto = "\u001B[40m";
	public static final String fundoVermelho = "\u001B[41m";
	public static final String fundoVerde = "\u001B[42m";
	public static final String fundoAmarelo = "\u001B[43m";
	public static final String fundoAzul = "\u001B[44m";
	public static final String fundoRoxo = "\u001B[45m";
	public static final String fundoCiano = "\u001B[46m";
	public static final String fundoBranco = "\u001B[47m";
  public static String peoesPretosCap;
	public static String peoesBrancosCap;
  public static String cavalosPretosCap;
	public static String cavalosBrancosCap;
  public static String torresPretasCap;
	public static String torresBrancasCap;
  public static String bisposPretosCap;
	public static String bisposBrancosCap;
  public static String damaPretaCap;
	public static String damaBrancaCap;
  public static String pecasCap;
  public static char[][] vetorPecasCap;
	

	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static PosicaoXadrez lerPosicaoXadrez (Scanner teclado) {
		try {
			//AQUI O SISEMA VAI RECEBER A POSIÇÃO ESCOLHIDA PELO USUÁRIO, COM TOLOWERCASE PARA ACEITAR MAISCULO OU MINUSCULO
	        String s = teclado.nextLine().toLowerCase();
		    char coluna = s.charAt(0);
		    int linha = Integer.parseInt(s.substring(1));
		    return new PosicaoXadrez(coluna, linha);
		}
		//SE FOR UMA POSIÇÃO FORA DAS POSIÇÕES EXISTENTES NO TABULEIRO, CHAMA A EXCEÇÃO
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro nas posições do tabuleiro");
		}
	}
	
	public static void imprimirPartida (PartidaXadrez partidaXadrez, List<PecaXadrez> pecasCap) {
		//COMEÇA IMPRIMINDO O ARRAY LIST DE PEÇAS CAPTURADAS EM TEMPO REAL
		imprimirPecasCapturadas(pecasCap);
		System.out.println();
		if (!partidaXadrez.getChequeMate()) {
		if (partidaXadrez.getJogadorAtual() == Cor.BRANCA) {
			if (partidaXadrez.getCheque() == true) {
				//IMPRIME O TABULEIRO NUM VISUAL DIFERENTE, PARA DESTACAR O CHEQUE
				imprimirTabuleiroEmCheque(partidaXadrez.getPecas(), fundoVermelho);
			} else {
		       imprimirTabuleiro(partidaXadrez.getPecas(), fundoAzul);
			}
		} 
		else {	
			if (partidaXadrez.getCheque() == true) {
				//IMPRIME O TABULEIRO EM VERMELHO, MAS NA VISÃO DAS PEÇAS PRETAS
				imprimirTabuleiroInvertidoEmCheque(partidaXadrez.getPecas(), fundoVermelho);
			} else {
			   imprimirTabuleiroInvertido(partidaXadrez.getPecas(), fundoAzul);
			}
		}
		
		System.out.println();
		
		if (partidaXadrez.getJogadorAtual() == Cor.BRANCA) {
		System.out.println("Jogada: " + amarelo+partidaXadrez.getVez()+ANSI_RESET);
		System.out.println("Jogam as : " + amarelo+ partidaXadrez.getJogadorAtual()+"S"+ANSI_RESET);
		}
		else {
			System.out.println("Jogada: " + vermelho+partidaXadrez.getVez()+ANSI_RESET);
			System.out.println("Jogam as: " + vermelho+ partidaXadrez.getJogadorAtual()+"S"+ANSI_RESET);
		}
		} 
		//AQUI ACABA O IF DO CHEQUE, ENTÃO SE A PEÇA NÃO ESTÁ EM CHEQUE, NEM EM POSIÇÃO NORMAL, ELA ESTÁ EM CHEQUE MATE
		//E IMPRIME O CHEQUE MATE NA VISÃO DAS PEÇAS QUE VENCERAM, QUE SERIA O JOGADOR ATUAL
		else {
			System.out.println(roxo+"              CHEQUE-MATE!"+ANSI_RESET);
			if (partidaXadrez.reiEstaEmChequeMate(Cor.BRANCA)) {
				imprimirTabuleiroEmChequeMate(partidaXadrez.getPecas(), fundoRoxo);
				System.out.println();
				System.out.println(roxo +"VITORIA DAS : " + amarelo+ partidaXadrez.getJogadorAtual()+"S"+ANSI_RESET + " POR " + roxo + "CHEQUE-MATE!");
				System.out.println("Jogadas: " + amarelo+partidaXadrez.getVez()+ANSI_RESET);
			} else {
				imprimirTabuleiroInvertidoEmChequeMate(partidaXadrez.getPecas(), fundoRoxo);
				System.out.println();
				System.out.println(roxo +"VITORIA DAS : " + vermelho+ partidaXadrez.getJogadorAtual()+"S"+ANSI_RESET + " POR " + roxo + "CHEQUE-MATE!");
			}
		}
	}
	
	public static void imprimirTabuleiroEmChequeMate(PecaXadrez[][] pecas, String cor) {
		System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+roxo+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 0; i < pecas.length; i++) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+roxo+":"+ANSI_RESET);
			for (int j = 0; j < pecas[i].length; j++) {
				imprimirPecasPretasEmChequeMate(pecas[i][j]);
			}
			//QUANDO CHEGA NO FINAL DA LINHA, SÓ QUEBRA A LINHA
			System.out.println(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			
			// EM SEGUIDA VEM AS DIVISÓRIAS DE CASAS DO TABULEIRO
			if (i < (pecas.length -1)) {
				//ENQUANTO ESTIVER NAS LINHAS DO MEIO, ELE IMPRIME COM CONEXÃO 
				//ENTRE AS LINHAS DE CIMA E DE BAIXO
				
			System.out.println(" " + cor + "  " + ANSI_RESET+roxo+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {

				System.out.println(" " + cor + "  " + ANSI_RESET+roxo+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
			}
		}
	}
	
	public static void imprimirTabuleiroInvertidoEmChequeMate(PecaXadrez[][] pecas, String cor) {
		System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+roxo+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 7; i >= 0; i--) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+roxo+":"+ANSI_RESET);
			for (int j = 7; j >= 0; j--) {
				imprimirPecasBrancasEmChequeMate(pecas[i][j]);
			}
			//QUANDO CHEGA NO FINAL DA LINHA, SÓ QUEBRA A LINHA
			System.out.println(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			
			// EM SEGUIDA VEM AS DIVISÓRIAS DE CASAS DO TABULEIRO
			if (i > (0)) {
				//ENQUANTO ESTIVER NAS LINHAS DO MEIO, ELE IMPRIME COM CONEXÃO 
				//ENTRE AS LINHAS DE CIMA E DE BAIXO
				
			System.out.println(" " + cor + "  " + ANSI_RESET+roxo+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {

				System.out.println(" " + cor + "  " + ANSI_RESET+roxo+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
			}
		}
	}

	public static void imprimirTabuleiro(PecaXadrez[][] pecas, String cor) {
		System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 0; i < pecas.length; i++) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+azul+":"+ANSI_RESET);
			for (int j = 0; j < pecas[i].length; j++) {
				imprimirPeca(pecas[i][j], false);
			}
			//QUANDO CHEGA NO FINAL DA LINHA, SÓ QUEBRA A LINHA
			System.out.println(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			
			// EM SEGUIDA VEM AS DIVISÓRIAS DE CASAS DO TABULEIRO
			if (i < (pecas.length -1)) {
				//ENQUANTO ESTIVER NAS LINHAS DO MEIO, ELE IMPRIME COM CONEXÃO 
				//ENTRE AS LINHAS DE CIMA E DE BAIXO
				
			System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {

				System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
			}
		}
	}
	
	public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis, String cor) {
		
		System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
		System.out.println(" " + cor+ "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 0; i < pecas.length; i++) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+azul+":"+ANSI_RESET);
			for (int j = 0; j < pecas[i].length; j++) {
				imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			//QUANDO CHEGA NO FINAL DA LINHA, SÓ QUEBRA A LINHA
			System.out.println(fundoAzul + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			
			// EM SEGUIDA VEM AS DIVISÓRIAS DE CASAS DO TABULEIRO
			if (i < (pecas.length -1)) {
				
			System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {

				System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
			}
		}
	}
	
	public static void imprimirTabuleiroEmCheque(PecaXadrez[][] pecas, String cor) {
		System.out.println(vermelho+"                CHEQUE!"+ANSI_RESET);
		System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 0; i < pecas.length; i++) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+vermelho+":"+ANSI_RESET);
			for (int j = 0; j < pecas[i].length; j++) {
				imprimirPecaEmCheque(pecas[i][j], false);
			}
			//QUANDO CHEGA NO FINAL DA LINHA, SÓ QUEBRA A LINHA
			System.out.println(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			
			// EM SEGUIDA VEM AS DIVISÓRIAS DE CASAS DO TABULEIRO
			if (i < (pecas.length -1)) {
				
			System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {

				System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
			}
		}
	}
	

	
	public static void imprimirTabuleiroInvertido(PecaXadrez[][] pecas, String cor) {
		System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 7; i >= 0; i--) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+azul+":"+ANSI_RESET);
			for (int j = 7; j >= 0; j--) {
				imprimirPeca(pecas[i][j], false);
			}
			//QUANDO CHEGA NO FINAL DA LINHA, SÓ QUEBRA A LINHA
			System.out.println(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			
			// EM SEGUIDA VEM AS DIVISÓRIAS DE CASAS DO TABULEIRO
			if (i > (0)) {
				
			System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {

				System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
			}
		}
	}
	
	
	public static void imprimirTabuleiroInvertido(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis, String cor) {
		System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 7; i >= 0; i--) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+azul+":"+ANSI_RESET);
			for (int j = 7; j >= 0; j--) {
				imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			//QUANDO CHEGA NO FINAL DA LINHA, SÓ QUEBRA A LINHA
			System.out.println(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			
			// EM SEGUIDA VEM AS DIVISÓRIAS DE CASAS DO TABULEIRO
			if (i > (0)) {
				
			System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {

				System.out.println(" " + cor + "  " + ANSI_RESET+azul+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
			}
		}
	}
	
	public static void imprimirTabuleiroEmCheque(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis, String cor) {
		
		System.out.println(vermelho+"                CHEQUE!"+ANSI_RESET);
		System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 7; i >= 0; i--) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+vermelho+":"+ANSI_RESET);
			for (int j = 7; j >=0 ; j--) {
				imprimirPecaEmCheque(pecas[i][j], movimentosPossiveis[i][j]);
			}
			//QUANDO CHEGA NO FINAL DA LINHA, SÓ QUEBRA A LINHA
			System.out.println(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			
			// EM SEGUIDA VEM AS DIVISÓRIAS DE CASAS DO TABULEIRO
			if (i < (pecas.length -1)) {
				
			System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {

				System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
			}
		}
	}
	
	public static void imprimirTabuleiroInvertidoEmCheque(PecaXadrez[][] pecas, String cor) {
		System.out.println(vermelho+"                CHEQUE!"+ANSI_RESET);
		System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 7; i >= 0; i--) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+vermelho+":"+ANSI_RESET);
			for (int j = 7; j >=0 ; j--) {
				imprimirPecaEmCheque(pecas[i][j], false);
			}
			//QUANDO CHEGA NO FINAL DA LINHA, SÓ QUEBRA A LINHA
			System.out.println(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			
			// EM SEGUIDA VEM AS DIVISÓRIAS DE CASAS DO TABULEIRO
			if (i > (0)) {
				
				System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {

				System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
			}
		}
	}
	
	public static void imprimirTabuleiroInvertidoEmCheque(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis, String cor) {
		System.out.println(vermelho+"                CHEQUE!"+ANSI_RESET);
		System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 7; i >= 0; i--) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+vermelho+":"+ANSI_RESET);
			for (int j = 7; j >=0 ; j--) {
				imprimirPecaEmCheque(pecas[i][j], movimentosPossiveis[i][j]);
			}
			//QUANDO CHEGA NO FINAL DA LINHA, SÓ QUEBRA A LINHA
			System.out.println(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			
			// EM SEGUIDA VEM AS DIVISÓRIAS DE CASAS DO TABULEIRO
			if (i > (0)) {
				
			System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {

				System.out.println(" " + cor + "  " + ANSI_RESET+vermelho+"+---+---+---+---+---+---+---+---+"+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
			}
		}
	}
	
	private static void imprimirPeca (PecaXadrez peca, boolean background) {

		if (background) {
			System.out.print(fundoAzul+" ");
		} else {
			System.out.print(" ");
		}
		
		//OU ELE IMPRIME UM ESPAÇO EM BRANCO, OU IMPRIME UMA PEÇA, E DEPOIS DELA,
			//IMPRIME A DIVISÓRIA COM A CASA DO LADO DIREITO
	if (peca == null) {
		System.out.print(" " + ANSI_RESET);
	} else {
		if (peca.getCor() == Cor.BRANCA) {
			//SE A PEÇA FOR BRANCA, ELE IMPRIME BRANCO NO CONSOLe
			System.out.print(amarelo + peca + ANSI_RESET);
		}
		else {
			//SE A PEÇA FOR PRETA, ELE IMPRIME ELA EM AMARELO
			System.out.print(vermelho + peca + ANSI_RESET);
		}
	}
	
	if (background) {
		System.out.print(fundoAzul+" "+ ANSI_RESET); 
	}	else {
		System.out.print(" ");
	} 
	System.out.print(azul+":"+ANSI_RESET);
		
	}
	
	private static void imprimirPecaEmCheque (PecaXadrez peca, boolean background) {

		if (background) {
			System.out.print(fundoVermelho+" ");
		} else {
			System.out.print(" ");
		}
		
		//OU ELE IMPRIME UM ESPAÇO EM BRANCO, OU IMPRIME UMA PEÇA, E DEPOIS DELA,
			//IMPRIME A DIVISÓRIA COM A CASA DO LADO DIREITO
	if (peca == null) {
		System.out.print(" " + ANSI_RESET);
	} else {
		if (peca.getCor() == Cor.BRANCA) {
			//SE A PEÇA FOR BRANCA, ELE IMPRIME BRANCO NO CONSOLe
		
			System.out.print(amarelo + peca + ANSI_RESET);
			
		
		}
		else {
			//SE A PEÇA FOR PRETA, ELE IMPRIME ELA EM AMARELO
			
			System.out.print(vermelho + peca + ANSI_RESET);
			
		}
	}
	
	 if (background) {
		System.out.print(fundoVermelho+" "+ ANSI_RESET);
	}
		else {
		System.out.print(" ");
	} 
	System.out.print(vermelho+":"+ANSI_RESET);
		
	}
	
	private static void imprimirPecasBrancasEmChequeMate (PecaXadrez peca) {

	if (peca == null) {
		System.out.print(roxo+ "  " + ANSI_RESET);
	} else {
		if (peca.getCor() == Cor.BRANCA) {
			//SE A PEÇA FOR BRANCA, ELE IMPRIME BRANCO NO CONSOLe
			if (peca.toString().equals("R") && peca.getCor() == Cor.PRETA) {
				System.out.print(fundoRoxo+" "+branco + peca);
			} else {
			System.out.print(" "+ amarelo + peca + ANSI_RESET);
			}
		
		}
		else {
			//SE A PEÇA FOR PRETA, ELE IMPRIME ELA EM VERMELHO
			System.out.print(vermelho + peca + ANSI_RESET);
		}
	}
	
	
	System.out.print(" "+ANSI_RESET);
	
	 
	System.out.print(roxo+":"+ANSI_RESET);
		
	}
	
	private static void imprimirPecasPretasEmChequeMate (PecaXadrez peca) {

if (peca == null) {
	System.out.print(roxo+ "  " + ANSI_RESET);
} else {
	if (peca.getCor() == Cor.BRANCA) {	
		System.out.print(" "+ amarelo + peca + ANSI_RESET);
	}
	else {
		//SE A PEÇA FOR PRETA, ELE IMPRIME ELA EM VERMELHO, MAS SE FOR O REI, IMPRIME ELE EM BRANCO
		if (peca.toString().equals("R") && peca.getCor() == Cor.PRETA) {
			System.out.print(fundoRoxo+" "+branco + peca);
		} else {
		System.out.print(" "+ vermelho + peca + ANSI_RESET);
		}
	}
}

System.out.print(" "+ANSI_RESET);
System.out.print(roxo+":"+ANSI_RESET);
	
}
	
	public static void imprimirPecasCapturadas(List<PecaXadrez> pecasCap) {
		//AQUI ELE FILTRA TODAS AS PEÇAS BRANCAS E PRETAS CAPTURADAS DURANTE O JOGO
		//EXISTE A LISTA DE TODAS AS PEÇAS CAPTURADAS, MAS AQUI ELE FILTRA POR COR
		List<PecaXadrez> brancasCap = pecasCap.stream().filter(x -> x.getCor() == Cor.BRANCA).collect(Collectors.toList());
		List<PecaXadrez> pretasCap = pecasCap.stream().filter(x -> x.getCor() == Cor.PRETA).collect(Collectors.toList());
		System.out.print(azul+"Pecas brancas capturadas: ");
		System.out.print(ANSI_RESET);
		System.out.println(amarelo +Arrays.toString(brancasCap.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print(azul +"Pecas pretas capturadas: ");
		System.out.println(vermelho+Arrays.toString(pretasCap.toArray()));
		System.out.print(ANSI_RESET);
	}
}