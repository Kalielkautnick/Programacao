package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.Cor;
import xadrez.ExceptionXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;


public class Programa{

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);				
		System.out.println("Escolha o estilo de jogo: \n(Xadrez tradicional: 1) \n(Xadrez aleatório: 2)");
		String opcao = sc.nextLine();
		PartidaXadrez partidaXadrez = null;
		while (!opcao.equals("1") && !opcao.equals("2")) {
			System.out.println("Opcao inválida. \n(Xadrez tradicional: 1) \n(Xadrez aleatório: 2)");
			opcao = sc.nextLine();
		}
		if (opcao.equals("1")) {
			partidaXadrez = new PartidaXadrez(1);
		} else {
			partidaXadrez = new PartidaXadrez(2);
		}
		
		List<PecaXadrez> captured = new ArrayList<>();
		//O PROGRAMA RODA ENQUANTO O REI NÃO ESTIVER EM CHEQUE MATE
		while (!partidaXadrez.getChequeMate()) {
			try {
				//PROGRAMA COMEÇA LIMPANDO A TELA TODA VEZ QUE É FEITO UMA JOGADA, E DEPOIS IMPRIME
				//O TABULEIRO, PEÇAS MORTAS, JOGADOR, ETC
				Interface.limparTela();
				System.out.println(Interface.azul+ "Jogo de Xadrez: "+ Interface.ANSI_RESET+ "by Kaliel Kautnick");
				Interface.imprimirPartida(partidaXadrez, captured);
				
				System.out.println("");
				System.out.println("Posicao da peca que a ser movida (ex: e4): ");
				PosicaoXadrez origem = null;
				try {
				//AQUI O SISEMA VAI RECEBER A POSIÇÃO ESCOLHIDA PELO USUÁRIO, COM TOLOWERCASE PARA ACEITAR MAISCULO OU MINUSCULO
	        	String s = sc.nextLine().toLowerCase();
		    	char coluna = s.charAt(0);
		    	int linha = Integer.parseInt(s.substring(1));
		    	origem = new PosicaoXadrez(coluna, linha);
				}
				//SE FOR UMA POSIÇÃO FORA DAS POSIÇÕES EXISTENTES NO TABULEIRO, CHAMA A EXCEÇÃO
				catch (RuntimeException e) {
					throw new InputMismatchException("Erro nas posições do tabuleiro - Press Enter");
				}				

				boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				//AGORA, O SISTEMA LIMPA A TELA NOVAMENTE, E VAI IMPRIMIR O TABULEIRO, DESTA VEZ COM OS MOVIMENTOS 
				//POSSÍVEIS DE CADA PEÇA, UMA MATRIZ BOOLEANA QUE DEIXA UM BACKGROUND COLORIDO NAS POSIÇÕES
				//QUE A PEÇA PODE FAZER.
				Interface.limparTela();				
				System.out.println(Interface.azul+ "Jogo de Xadrez: "+ Interface.ANSI_RESET+ "by Kaliel Kautnick");
					System.out.println();
				//SE A PEÇA FOR BRANCA, ELE CHAMA A IMPRESSÃO DO TABULEIRO NA VISÃO DAS BRANCAS
				if (partidaXadrez.getJogadorAtual() == Cor.BRANCA) {
					//SE O TABULEIRO ESTIVER EM CHEQUE, ENTÃO ELE CHAMA A IMPRESSÃO DO TABULEIRO EM CHEQUE					
					if (partidaXadrez.getCheque() == true) {
						System.out.println(Interface.vermelho+"                CHEQUE!"+Interface.ANSI_RESET);
						Interface.imprimirTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis, 
										Interface.fundoVermelho,captured);
					} else {
				         Interface.imprimirTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis, 
						 				Interface.fundoAzul,captured);
					}
				} 
				//SE A PECA NÃO FOR BRANCA, ELE IMPRIME O TABULEIRO INVERTIDO, NA VISÃO DAS PRETAS.
				//TAMBÉM TEM A OPÇÃO DE IMPRIMIR EM CHEQUE (VERMELHO) NA VISÃO DAS PRETAS
				else {
					if (partidaXadrez.getCheque() == true) {
						System.out.println(Interface.vermelho+"                CHEQUE!"+Interface.ANSI_RESET);
						Interface.imprimirTabuleiroInvertido(partidaXadrez.getPecas(), 
															movimentosPossiveis, Interface.fundoVermelho,
															captured);
					} else {
					Interface.imprimirTabuleiroInvertido(partidaXadrez.getPecas(), 
															movimentosPossiveis, Interface.fundoAzul,
															captured);
					}
				}

				System.out.println("");
				System.out.println("Posicao de destino da peca (ex: d4): ");
				PosicaoXadrez destino = null;
				try {
				//AQUI O SISEMA VAI RECEBER A POSIÇÃO ESCOLHIDA PELO USUÁRIO, COM TOLOWERCASE PARA ACEITAR MAISCULO OU MINUSCULO
	        	String s = sc.nextLine().toLowerCase();
		    	char coluna = s.charAt(0);
		    	int linha = Integer.parseInt(s.substring(1));
		    	destino = new PosicaoXadrez(coluna, linha);
				}
				//SE FOR UMA POSIÇÃO FORA DAS POSIÇÕES EXISTENTES NO TABULEIRO, CHAMA A EXCEÇÃO
				catch (RuntimeException e) {
					throw new InputMismatchException("Erro nas posições do tabuleiro - Press Enter");
				}

				PecaXadrez pecaCapturada = partidaXadrez.movimentarPeca(origem, destino);

				//AQUI O SISTEMA COMEÇA A ADICIONAR AS PEÇAS CAPTURADAS NO ARRAY LIST
				if (pecaCapturada != null) {
						captured.add(pecaCapturada);
				}
				
				//AQUI ELE TESTA SE EXISTE ALGUMA PEÇA PROMOVIDA, E PEDE PARA O USUÁRIO ESCOLHER QUAL PEÇA IRÁ SUBSTITUIR
				if (partidaXadrez.getPromovida() != null) {
					//A PESSOA PODE ESCOLHER AS LETRAS RESPECTIVAS AS PEÇAS, PARA ENTÃO CHAMAR O MÉTODO E SUBSTITUIR
					//O PEÃO PELA PEÇA ESCOLHIDA
					System.out.print("Escolha a peca para promover:");
					System.out.print(Interface.verde+"D(dama)"+Interface.ANSI_RESET+" - ");
					System.out.print(Interface.ciano+"C(cavalo)"+Interface.ANSI_RESET+" - ");
					System.out.print(Interface.azul+"T(torre)"+Interface.ANSI_RESET+" - ");
					System.out.print(Interface.vermelho+"B(bispo)"+Interface.ANSI_RESET+" - ");
					String tipo = sc.nextLine().toUpperCase();
					//ENQUANTO O USUÁRIO NÃO DIGITAR AS OPÇÕES VALIDAS, ELE REPETE
						while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") & !tipo.equals("D")) {
							System.out.print("Peca Invalida! Escolha a peca para promover: ");
							System.out.print(Interface.verde+"D(dama)"+Interface.ANSI_RESET+" - ");
							System.out.print(Interface.ciano+"C(cavalo)"+Interface.ANSI_RESET+" - ");
							System.out.print(Interface.azul+"T(torre)"+Interface.ANSI_RESET+" - ");
							System.out.print(Interface.vermelho+"B(bispo)"+Interface.ANSI_RESET+" - ");
						tipo = sc.nextLine().toUpperCase();
					}
					partidaXadrez.alterarPecaPromovida(tipo);
				}
			}
			catch (ExceptionXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		//QUANDO HOUVER UM CHEQUE MATE, ELE VAI SAIR O WHILE, E VAI VIR PARA CÁ
		//IMPRIMINDO NA TELA O TABULEIRO CHEQUE MATE (EM ROXO, E DESTANCANDO O REI MORTO COM BACKGROUND ROXO)
		Interface.limparTela();
		Interface.imprimirPartida(partidaXadrez, captured);
		sc.close();

	}

}