package application;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;


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

	public static String divisoria = "+---+---+---+---+---+---+---+---+";
    public static String pecasCap;
	public static int vantagemMaterial;
	public static boolean brancasMaisMaterial;
	public static boolean pretasMaisMaterial;
	

	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
		
	public static void imprimirPartida (PartidaXadrez partidaXadrez, List<PecaXadrez> pecasCap) {
		//COMEÇA IMPRIMINDO O ARRAY LIST DE PEÇAS CAPTURADAS EM TEMPO REAL
		System.out.println();
		if (!partidaXadrez.getChequeMate()) {
		vantagemMaterial = getValorMaterial(pecasCap);
		brancasMaisMaterial = vantagemMaterial > 0 ? true : false;
		pretasMaisMaterial = vantagemMaterial < 0 ? true : false;
		if (partidaXadrez.getJogadorAtual() == Cor.BRANCA) {
			if (partidaXadrez.getCheque() == true) {
				//IMPRIME O TABULEIRO NUM VISUAL DIFERENTE, PARA DESTACAR O CHEQUE
				System.out.println(vermelho+"                CHEQUE!"+ANSI_RESET);
				imprimirTabuleiro(partidaXadrez.getPecas(), fundoVermelho, pecasCap);
			} else {
		       imprimirTabuleiro(partidaXadrez.getPecas(), fundoAzul, pecasCap);
			}
		}
		else {
			if (partidaXadrez.getCheque() == true) {
				//IMPRIME O TABULEIRO EM VERMELHO, MAS NA VISÃO DAS PEÇAS PRETAS
				System.out.println(vermelho+"                CHEQUE!"+ANSI_RESET);
				imprimirTabuleiroInvertido(partidaXadrez.getPecas(), fundoVermelho, pecasCap);
			} else {
			   imprimirTabuleiroInvertido(partidaXadrez.getPecas(), fundoAzul, pecasCap);
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
				imprimirTabuleiro(partidaXadrez.getPecas(), fundoRoxo,pecasCap);
				System.out.println();
				System.out.println(roxo +"VITORIA DAS : " + amarelo+ partidaXadrez.getJogadorAtual()+"S"+ANSI_RESET + " POR " + roxo + "CHEQUE-MATE!");
				System.out.println("Jogadas: " + amarelo+partidaXadrez.getVez()+ANSI_RESET);
			} else {
				imprimirTabuleiroInvertido(partidaXadrez.getPecas(), fundoRoxo, pecasCap);
				System.out.println();
				System.out.println(roxo +"VITORIA DAS : " + vermelho+ partidaXadrez.getJogadorAtual()+"S"+ANSI_RESET + " POR " + roxo + "CHEQUE-MATE!");
			}
		}
	}
	
	public static void imprimirTabuleiro(PecaXadrez[][] pecas, String cor, List<PecaXadrez> pecasCap) {
		String corLinha = "";
		if (cor == fundoVermelho) {
			corLinha = vermelho;
		} else if (cor == fundoRoxo) {
			corLinha = roxo;
		} else {
			corLinha = azul;
		}
		System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 0; i < pecas.length; i++) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+corLinha+":"+ANSI_RESET);
			for (int j = 0; j < pecas[i].length; j++) {
				if (cor == fundoVermelho) {
				imprimirPecaEmCheque(pecas[i][j], false);
				} else if (cor == fundoRoxo) {
					imprimirPecasBrancasEmChequeMate(pecas[i][j]);
				} else {
					imprimirPeca(pecas[i][j], false);
				}				
			}	
				
			System.out.print(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			if (i == 2 || i == 4) {
				System.out.print("       ");
				if (i == 2) {
					imprimirCapturadas(pecasCap, Cor.BRANCA);
				} else if (i == 4) {
					imprimirCapturadas(pecasCap, Cor.PRETA);
				}				
			}	else {
				System.out.print("\n");
			}	
			////////////////////////				
			if (i < (pecas.length -1)) {						
			System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {
				System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
			}
		}
	}
	
	public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis, String cor, List<PecaXadrez> pecasCap) {
		String corLinha = "";
		if (cor == fundoVermelho) {
			corLinha = vermelho;
		} else if (cor == fundoRoxo) {
			corLinha = roxo;
		} else {
			corLinha = azul;
		}
		System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
		System.out.println(" " + cor+ "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 0; i < pecas.length; i++) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+corLinha+":"+ANSI_RESET);
			for (int j = 0; j < pecas[i].length; j++) {
				if (cor == fundoVermelho) {
				imprimirPecaEmCheque(pecas[i][j], movimentosPossiveis[i][j]);
				} else if (cor == fundoRoxo) {
					imprimirPecasBrancasEmChequeMate(pecas[i][j]);
				} else {
					imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
				}				
			}			
			System.out.print(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			if (i == 2 || i == 4) {
				System.out.print("       ");
				if (i == 2) {
					imprimirCapturadas(pecasCap, Cor.BRANCA);
				} else if (i == 4) {
					imprimirCapturadas(pecasCap, Cor.PRETA);
				}				
			}	else {
				System.out.print("\n");
			}
			if (i < (pecas.length -1)) {
			System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {
				System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  a   b   c   d   e   f   g   h    "+ANSI_RESET);
			}
		}
	}
	
	public static void imprimirTabuleiroInvertido(PecaXadrez[][] pecas, String cor, List<PecaXadrez> pecasCap) {
		String corLinha = "";
		if (cor == fundoVermelho) {
			corLinha = vermelho;
		} else if (cor == fundoRoxo) {
			corLinha = roxo;
		} else {
			corLinha = azul;
		}
		System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 7; i >= 0; i--) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+corLinha+":"+ANSI_RESET);
			for (int j = 7; j >= 0; j--) {
				if (cor == fundoVermelho) {
				imprimirPecaEmCheque(pecas[i][j], false);
				} else if (cor == fundoRoxo) {
					imprimirPecasPretasEmChequeMate(pecas[i][j]);
				} else {
					imprimirPeca(pecas[i][j], false);
				}				
			}			
			System.out.print(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			if (i == 3 || i == 5) {
				System.out.print("       ");
				if (i == 5) {
					imprimirCapturadas(pecasCap, Cor.BRANCA);
				} else if (i == 3) {
					imprimirCapturadas(pecasCap, Cor.PRETA);
				}				
			}	else {
				System.out.print("\n");
			}	
			if (i > (0)) {				
			System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {
				System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
			}
		}
	}
	
	public static void imprimirTabuleiroInvertido(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis, String cor, List<PecaXadrez> pecasCap) {		
		String corLinha = "";
		if (cor == fundoVermelho) {
			corLinha = vermelho;
		} else if (cor == fundoRoxo) {
			corLinha = roxo;
		} else {
			corLinha = azul;
		}
		System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
		System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
		for (int i = 7; i >= 0; i--) {
			System.out.print(" ");
			System.out.print(cor+" "+ preto+ (8-i) +ANSI_RESET+corLinha+":"+ANSI_RESET);
			for (int j = 7; j >= 0; j--) {
				if (cor == fundoVermelho) {
				imprimirPecaEmCheque(pecas[i][j], movimentosPossiveis[i][j]);
				} else if (cor == fundoRoxo) {
					imprimirPecasPretasEmChequeMate(pecas[i][j]);
				} else {
					imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
				}				
			}			
			System.out.print(cor + " "+ preto+ (8-i) +ANSI_RESET + ANSI_RESET);
			if (i == 3 || i == 5) {
				System.out.print("       ");
				if (i == 5) {
					imprimirCapturadas(pecasCap, Cor.BRANCA);
				} else if (i == 3) {
					imprimirCapturadas(pecasCap, Cor.PRETA);
				}				
			}	else {
				System.out.print("\n");
			}					
			if (i > (0)) {				
			System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
			} else {
				System.out.println(" " + cor + "  " + ANSI_RESET+corLinha+divisoria+ANSI_RESET + cor + "  " + ANSI_RESET);
				System.out.println(" "+cor+"  "+preto+"  h   g   f   e   d   c   b   a    "+ANSI_RESET);
			}
		}
	}
		
	private static void imprimirPeca(PecaXadrez peca, boolean background) {
		if (background) {
			System.out.print(fundoAzul+" ");
		} else {
			System.out.print(" ");
		}				
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
				//SE A PEÇA FOR BRANCA, ELE IMPRIME BRANCO NO CONSOLE
				System.out.print(amarelo + peca + ANSI_RESET);
			} else {
				//SE A PEÇA FOR PRETA, ELE IMPRIME ELA EM AMARELO
				System.out.print(vermelho + peca + ANSI_RESET);		
			}
		}
		if (background) {
			System.out.print(fundoVermelho+" "+ ANSI_RESET);
		} else {
			System.out.print(" ");
		} 
		System.out.print(vermelho+":"+ANSI_RESET);
		}
	
private static void imprimirPecasBrancasEmChequeMate(PecaXadrez peca) {
	if (peca == null) {
		System.out.print(roxo+ "  " + ANSI_RESET);
	} else {
		if (peca.getCor() == Cor.PRETA) {
		    System.out.print(" "+ vermelho + peca + ANSI_RESET);
		} else {
		   //SE A PEÇA FOR BRANCA, ELE IMPRIME BRANCO NO CONSOLe
		   if (peca.toString().equals("R") && peca.getCor() == Cor.BRANCA) {
		 	System.out.print(fundoRoxo+" "+preto + peca);
		    } else {
			System.out.print(" "+ amarelo + peca + ANSI_RESET);
		    }		
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
					System.out.print(fundoRoxo+" "+preto + peca);
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
		List<PecaXadrez> brancasCap = pecasCap.stream().filter(x -> x.getCor() == Cor.BRANCA && x != null).collect(Collectors.toList());
		List<PecaXadrez> pretasCap = pecasCap.stream().filter(x -> x.getCor() == Cor.PRETA && x != null).collect(Collectors.toList());
		System.out.print(azul+"Pecas brancas capturadas: ");
		System.out.print(ANSI_RESET);
		System.out.println(amarelo +Arrays.toString(brancasCap.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print(azul +"Pecas pretas capturadas: ");
		System.out.println(vermelho+Arrays.toString(pretasCap.toArray()));
		System.out.print(ANSI_RESET);
	}

	public static int getValorMaterial(List<PecaXadrez> pecasCap){
		int valorMaterial = 0;
		if (pecasCap != null) {
			List<PecaXadrez> capturadas = pecasCap;
			for (PecaXadrez p : capturadas) {
					if (p.getCor() == Cor.BRANCA) {
					valorMaterial += p.getValorMaterial();
					} else {
						valorMaterial -= p.getValorMaterial();
					}
			}			
			return valorMaterial;
		} else {
		return 0;
		}
	}

	public static void imprimirCapturadas(List<PecaXadrez> pecasCap, Cor color) {
		//AQUI ELE FILTRA TODAS AS PEÇAS BRANCAS E PRETAS CAPTURADAS DURANTE O JOGO
		//EXISTE A LISTA DE TODAS AS PEÇAS CAPTURADAS, MAS AQUI ELE FILTRA POR COR
		String listaPecas = "";
		List<PecaXadrez> peoesCapturados = pecasCap.stream().filter(x -> x.getCor() == color && x.toString().equals("P")).collect(Collectors.toList());
		if (!peoesCapturados.isEmpty()) {
			listaPecas += Arrays.toString(peoesCapturados.toArray()).replace("P, P", "PP").replace(", P", "P");;
		}
		peoesCapturados = null;
		List<PecaXadrez> bisposCapturados = pecasCap.stream().filter(x -> x.getCor() == color && x.toString().equals("B")).collect(Collectors.toList());
		if (!bisposCapturados.isEmpty()) {
			if (!listaPecas.equals("")) {
				listaPecas += ", ";
			}
			listaPecas += Arrays.toString(bisposCapturados.toArray()).replace("B, B", "BB");;
		}
		bisposCapturados = null;
		List<PecaXadrez> cavalosCapturados = pecasCap.stream().filter(x -> x.getCor() == color && x.toString().equals("C")).collect(Collectors.toList());
		if (!cavalosCapturados.isEmpty()) {
			if (!listaPecas.equals("")) {
				listaPecas += ", ";
			}
			listaPecas += Arrays.toString(cavalosCapturados.toArray()).replace("C, C", "CC");
		}
		cavalosCapturados = null;
		List<PecaXadrez> damasCapturadas = pecasCap.stream().filter(x -> x.getCor() == color && x.toString().equals("D")).collect(Collectors.toList());
		if (!damasCapturadas.isEmpty()) {
			if (!listaPecas.equals("")) {
				listaPecas += ", ";
			}
			listaPecas += "D";
		}
		damasCapturadas = null;
		List<PecaXadrez> torresCapturadas = pecasCap.stream().filter(x -> x.getCor() == color && x.toString().equals("T")).collect(Collectors.toList());
		if (!torresCapturadas.isEmpty()) {
			if (!listaPecas.equals("")) {
				listaPecas += ", ";
			}
			listaPecas += Arrays.toString(torresCapturadas.toArray()).replace("T, T", "TT");
		}
		torresCapturadas = null;
		listaPecas = listaPecas.replace("[", "");
		listaPecas = listaPecas.replace("]", "");
		
		String s = color == Cor.BRANCA ? "Brancas " : "Pretas ";
		System.out.print(azul+s+"capturadas: ");
		System.out.print(ANSI_RESET);
		if (color == Cor.BRANCA) {
			if (brancasMaisMaterial) {
				System.out.println(amarelo +"["+listaPecas+"]+"+vantagemMaterial);
			} else {
				System.out.println(amarelo +"["+listaPecas+"]");
			}
			
		} else {
			if (pretasMaisMaterial) {
				System.out.println(vermelho +"["+listaPecas+"]+"+(vantagemMaterial * -1));
			} else {
				System.out.println(vermelho +"["+listaPecas+"]");
			}
		}
		System.out.print(ANSI_RESET);
	}
}