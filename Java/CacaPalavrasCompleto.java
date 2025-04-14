//Esse é um caça palavras 15x15 que procura as palavras em todas as 8 direções, feito por mim.

import java.util.Scanner;

public class CacaPalavrasCompleto {
    public boolean[][] mapaBoolean = new boolean[15][15];
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    	public static final String ANSI_RESET = "\u001B[0m";

  //método construtor:
  private CacaPalavrasCompleto() {
    Scanner entrada = new Scanner(System.in);
    char[][] mapa = new char[15][15];
  
    String[][] palavras = new String[10][2];
    int mapaQuantLinhas = 15;
    int mapaQuantColunas = 15;
    palavrasEntrada(palavras);
    mapaEntrada(mapa);
    mapaPesquisar(mapaQuantLinhas, mapaQuantColunas, mapa, palavras); //nesse método, será chamado chamado outros métodos que irão alimentar o mapa Booleano.
    int opcao = 0;
    //menu de 3 opções:
    do {
      System.out.println("_____ Menu: Caça Palavras _____");
      System.out.println("1. listar palavras");
      System.out.println("2. mostrar caça-palavras");
      System.out.println("3. listar respostas");
      System.out.println("4. mostrar respostas no caca-palavras");
      System.out.println("5. sair");
      System.out.print(" __ opção: ");
      opcao = entrada.nextInt();
      switch (opcao) {
        case 1:
          imprimirPalavras(palavras);
          break;
        case 2:
          imprimirMapa(mapa, mapaQuantLinhas, mapaQuantColunas, palavras);
          break;
        case 3:
          respostasPalavras(palavras);
          break;
        case 4:
          imprimirMapaBoolean(mapa, mapaQuantLinhas, mapaQuantColunas, palavras);
          break;
        case 5:
          System.out.println("Programa encerrado.");
          break;
        default:
          System.out.println("Opção errada!...");
          break;
      }
    } 
    while (opcao != 5);
    entrada.close();
  }
  
//método onde coloco as palavras que terá no meu caça palavras
  private void palavrasEntrada(String[][] palavras) {
    palavras[0][0] = "JAVA";
    palavras[1][0] = "PYTHON";
    palavras[2][0] = "RUBY";
    palavras[3][0] = "DELPHI";
    palavras[4][0] = "COBOL";
    palavras[5][0] = "FORTRAN";
    palavras[6][0] = "JAvASCRIPT";
    palavras[7][0] = "HTML";
    palavras[8][0] = "BASIC";
    palavras[9][0] = "ASSEMBLY";
  }

//caça palavra inteiro, todas as letras com suas respectivas posições.
  private void mapaEntrada(char[][] mapa) {
    mapa[0][0] = 'Y';  mapa[0][1] = 'K';  mapa[0][2] = 'E';  mapa[0][3] = 'D';  mapa[0][4] = 'M';
    mapa[0][5] = 'W';  mapa[0][6] = 'U';  mapa[0][7] = 'P';  mapa[0][8] = 'S';  mapa[0][9] = 'D';
    mapa[0][10]= 'L';  mapa[0][11]= 'U';  mapa[0][12]= 'P';  mapa[0][13]= 'K'; mapa[0][14]= 'E';

    mapa[1][0] = 'L';  mapa[1][1] = 'O';  mapa[1][2] = 'Y';  mapa[1][3] = 'B';  mapa[1][4] = 'A';
    mapa[1][5] = 'S';  mapa[1][6] = 'I';  mapa[1][7] = 'C';  mapa[1][8] = 'I';  mapa[1][9] = 'E';
    mapa[1][10]= 'D';  mapa[1][11]= 'M';  mapa[1][12]= 'W';  mapa[1][13]= 'I';  mapa[1][14]= 'F';

    mapa[2][0] = 'B';  mapa[2][1] = 'U';  mapa[2][2] = 'N';  mapa[2][3] = 'J';  mapa[2][4] = 'C';
    mapa[2][5] = 'B';  mapa[2][6] = 'Y';  mapa[2][7] = 'F';  mapa[2][8] = 'K';  mapa[2][9] = 'L';
    mapa[2][10]= 'O';  mapa[2][11]= 'B';  mapa[2][12]= 'O';  mapa[2][13]= 'C';  mapa[2][14]= 'X';

    mapa[3][0] = 'M';  mapa[3][1] = 'W';  mapa[3][2] = 'D';  mapa[3][3] = 'A';  mapa[3][4] = 'Q';
    mapa[3][5] = 'S';  mapa[3][6] = 'R';  mapa[3][7] = 'L';  mapa[3][8] = 'I';  mapa[3][9] = 'P';
    mapa[3][10]= 'Q';  mapa[3][11]= 'G';  mapa[3][12]= 'U';  mapa[3][13]= 'L';  mapa[3][14]= 'O';

    mapa[4][0] = 'E';  mapa[4][1] = 'F';  mapa[4][2] = 'I';  mapa[4][3] = 'J';  mapa[4][4] = 'H';
    mapa[4][5] = 'E';  mapa[4][6] = 'B';  mapa[4][7] = 'K';  mapa[4][8] = 'G';  mapa[4][9] = 'H';
    mapa[4][10]= 'E';  mapa[4][11]= 'S';  mapa[4][12]= 'X';  mapa[4][13]= 'B';  mapa[4][14]= 'N';

    mapa[5][0] = 'S';  mapa[5][1] = 'M';  mapa[5][2] = 'P';  mapa[5][3] = 'X';  mapa[5][4] = 'A';
    mapa[5][5] = 'D';  mapa[5][6] = 'O';  mapa[5][7] = 'G';  mapa[5][8] = 'B';  mapa[5][9] = 'I';
    mapa[5][10]= 'F';  mapa[5][11]= 'O';  mapa[5][12]= 'H';  mapa[5][13]= 'O';  mapa[5][14]= 'A';

    mapa[6][0] = 'S';  mapa[6][1] = 'M';  mapa[6][2] = 'N';  mapa[6][3] = 'G';  mapa[6][4] = 'F';
    mapa[6][5] = 'v';  mapa[6][6] = 'F';  mapa[6][7] = 'L';  mapa[6][8] = 'N';  mapa[6][9] = 'S';   
    mapa[6][10]= 'A';  mapa[6][11]= 'I';  mapa[6][12]= 'H';  mapa[6][13]= 'U';  mapa[6][14]= 'M';

    mapa[7][0] = 'A';  mapa[7][1] = 'L';  mapa[7][2] = 'U';  mapa[7][3] = 'R';  mapa[7][4] = 'I';
    mapa[7][5] = 'H';  mapa[7][6] = 'A';  mapa[7][7] = 'Y';  mapa[7][8] = 'U';  mapa[7][9] = 'L';
    mapa[7][10]= 'B';  mapa[7][11]= 'T';  mapa[7][12]= 'P';  mapa[7][13]= 'Z';  mapa[7][14]= 'Y';

    mapa[8][0] = 'B';  mapa[8][1] = 'Q';  mapa[8][2] = 'F';  mapa[8][3] = 'Z';  mapa[8][4] = 'A';   
    mapa[8][5] = 'P';  mapa[8][6] = 'O';  mapa[8][7] = 'S';  mapa[8][8] = 'V';  mapa[8][9] = 'Y';
    mapa[8][10]= 'Y';  mapa[8][11]= 'O';  mapa[8][12]= 'N';  mapa[8][13]= 'R';  mapa[8][14]= 'F';

    mapa[9][0] = 'S';  mapa[9][1] = 'K';  mapa[9][2] = 'O';  mapa[9][3] = 'F';  mapa[9][4] = 'X';
    mapa[9][5] = 'G';  mapa[9][6] = 'K';  mapa[9][7] = 'E';  mapa[9][8] = 'C';  mapa[9][9] = 'P';
    mapa[9][10]= 'A';  mapa[9][11]= 'J';  mapa[9][12]= 'W';  mapa[9][13]= 'U';  mapa[9][14]= 'S';

    mapa[10][0] = 'N';  mapa[10][1] = 'A';  mapa[10][2] = 'R';  mapa[10][3] = 'H';  mapa[10][4] = 'X';
    mapa[10][5] = 'A';  mapa[10][6] = 'W';  mapa[10][7] = 'U';  mapa[10][8] = 'K';  mapa[10][9] = 'R';
    mapa[10][10]= 'X';  mapa[10][11]= 'S';  mapa[10][12]= 'Q';  mapa[10][13]= 'B';  mapa[10][14]= 'D';

    mapa[11][0] = 'E';  mapa[11][1] = 'M';  mapa[11][2] = 'T';  mapa[11][3] = 'E';  mapa[11][4] = 'B';
    mapa[11][5] = 'H';  mapa[11][6] = 'V';  mapa[11][7] = 'M';  mapa[11][8] = 'N';  mapa[11][9] = 'J';
    mapa[11][10]= 'I';  mapa[11][11]= 'Y';  mapa[11][12]= 'O';  mapa[11][13]= 'Y';  mapa[11][14]= 'M';

    mapa[12][0] = 'Q';  mapa[12][1] = 'M';  mapa[12][2] = 'R';  mapa[12][3] = 'D';  mapa[12][4] = 'L';
    mapa[12][5] = 'U';  mapa[12][6] = 'Z';  mapa[12][7] = 'A';  mapa[12][8] = 'S';  mapa[12][9] = 'A';
    mapa[12][10]= 'Q';  mapa[12][11]= 'P';  mapa[12][12]= 'N';  mapa[12][13]= 'I';  mapa[12][14]= 'W';

    mapa[13][0] = 'L';  mapa[13][1] = 'P';  mapa[13][2] = 'A';  mapa[13][3] = 'O';  mapa[13][4] = 'X';
    mapa[13][5] = 'V';  mapa[13][6] = 'F';  mapa[13][7] = 'H';  mapa[13][8] = 'J';  mapa[13][9] = 'B';
    mapa[13][10]= 'P';  mapa[13][11]= 'D';  mapa[13][12]= 'T';  mapa[13][13]= 'E';  mapa[13][14]= 'O';

    mapa[14][0] = 'U';  mapa[14][1] = 'J';  mapa[14][2] = 'N';  mapa[14][3] = 'S';  mapa[14][4] = 'Z';
    mapa[14][5] = 'J';  mapa[14][6] = 'E';  mapa[14][7] = 'Y';  mapa[14][8] = 'I';  mapa[14][9] = 'S';
    mapa[14][10]= 'B';  mapa[14][11]= 'K';  mapa[14][12]= 'F';  mapa[14][13]= 'U';  mapa[14][14]= 'Q';
  }

//método da opção 1, imprime as palavras que serão procuras.
  public void imprimirPalavras(String[][] palavras) {
    for (int i = 0; i < palavras.length; i++) System.out.println(
      palavras[i][0]
    );
  }

//método da opção 3, imprime na tela se a palavra foi encontrada ou não, se foi encontrada, imprime a POSIÇÃO da palavra dentro do mapa.
  public void respostasPalavras(String[][] palavras) {
    for (int i = 0; i < palavras.length; i++) {
      String achouPalavra = palavras[i][1];
      if (achouPalavra == null) {
        System.out.println("Palavra NÃO encontrada: " + palavras[i][0]);
      } else {
        System.out.print("(linha,coluna): ");
        System.out.println((palavras[i][1]) + " - " + palavras[i][0]);
      }
    }
  }

//imprime o caça palavras completo na tela, bem formatado, realmente tem uma boa dificuldade para encontrar as palavras visualmente.
  public void imprimirMapa(char[][] mapa, int mapaQtdLinha, int mapaQtdColuna, String [][] palavras) {
    for (int i = 0; i <= 4; i++) {
      System.out.print(palavras[i][0] + " - ");
  }
    System.out.println("");
    for (int i = 5; i <= 9; i++) {
     System.out.print(palavras[i][0] + " - ");
    }
    System.out.println("");
    System.out.print("   ");
    for (int i = 0; i < mapaQtdColuna; i++) {
      if (i < 10) {
      System.out.print(" 0" + i + " ");
    } else if (i <= 15) {
      System.out.print(" " + i + " ");
     }
    }
    System.out.println("");
    
    System.out.println("   -------------------------------------------------------------");
    for (int i = 0; i < mapaQtdLinha; i++) {
      if (i < 10) {
      System.out.print("0" + i + " ");
      } else if (i < 15) {
        System.out.print(i + " ");
      }
      for (int j = 0; j < mapaQtdColuna; j++) {
        System.out.print("| " + mapa[i][j] + " ");
        }
        
      System.out.println("|");
      System.out.println("   -------------------------------------------------------------");
    }
  }

  //imprime o caça palavras completo na tela, bem formatado, realmente tem uma boa dificuldade para encontrar as palavras visualmente.
  public void imprimirMapaBoolean(char[][] mapa, int mapaQtdLinha, int mapaQtdColuna, String [][] palavras) {
    for (int i = 0; i <= 4; i++) {
        System.out.print(palavras[i][0] + " - ");
    }
      System.out.println("");
      for (int i = 5; i <= 9; i++) {
       System.out.print(palavras[i][0] + " - ");
      }
      System.out.println("");
      System.out.print("   ");
      for (int i = 0; i < mapaQtdColuna; i++) {
        if (i < 10) {
        System.out.print(" 0" + i + " ");
      } else if (i <= 15) {
        System.out.print(" " + i + " ");
       }
      }
      System.out.println("");
      
      System.out.println("   -------------------------------------------------------------");
      for (int i = 0; i < mapaQtdLinha; i++) {
        if (i < 10) {
        System.out.print("0" + i + " ");
        } else if (i < 15) {
          System.out.print(i + " ");
        }
        for (int j = 0; j < mapaQtdColuna; j++) {
          if (mapaBoolean[i][j]) {
          System.out.print("|"+ ANSI_BLUE_BACKGROUND + " " + ANSI_RED + mapa[i][j] + " " + ANSI_RESET);
          } else {
            System.out.print("| " + mapa[i][j] + " ");
          }
        }
          
        System.out.println("|");
        System.out.println("   -------------------------------------------------------------");
      }
  }

//método para procurar as palavras da esquerda para a direita
  private void esquerdaParaDireita (int palavraAtual,int mapaQtdLinha,int mapaQtdColuna,char[][] mapa,
    String[][] palavras) {
    // percorre colunas da esquerda para a direita
    int letraAtual = 0;
    for (int lin = 0; lin < mapaQtdLinha; lin++) {
      for (int col = 0; col < mapaQtdColuna; col++) {
         //melhorar desempenho, dar break quando não precisa mais procurar
         if (col > (mapaQtdColuna - palavras[palavraAtual][0].length()) && letraAtual == 0) {
          break;
        }
        else if (mapa[lin][col] == palavras[palavraAtual][0].charAt(letraAtual)) {
          letraAtual += 1;
            //compara se o tamanho da letra é do mesmo tamanho da palavra, se for verdade, a palavra inteira foi encontrada no mapa
          if (letraAtual == palavras[palavraAtual][0].length()) {
          //se encontrar a palavra inteira, a coluna 1 da matriz "palavra" vai receber a posição da primeira letra no mapa.
            palavras[palavraAtual][1] = "[" + lin + "," + (col - palavras[palavraAtual][0].length() + 1) + "]";
            for (int j = 0; j < palavras[palavraAtual][0].length(); j++) {
                mapaBoolean[lin][col - j] = true;
            }
            letraAtual = 0;
            break;
          }
        } else {
          letraAtual = 0;
        }
      }
    }
  }

  //método para procurar as palavras da direita para a esquerda
  private void direitaParaEsquerda (int palavraAtual,int mapaQtdLinha,int mapaQtdColuna,char[][] mapa,String[][] palavras) {
    //percorre colunas da direita pra esquerda
    int letraAtual = 0;
    for (int lin = 0; lin < mapaQtdLinha; lin++) {
      for (int col = (mapaQtdColuna - 1); col >= 0; col -= 1) {
        //melhorar desempenho, dar break quando não precisa mais procurar
        if (col < (palavras[palavraAtual][0].length() - 1) && letraAtual == 0) {
          break;
        } else if (mapa[lin][col] == palavras[palavraAtual][0].charAt(letraAtual)) {
          letraAtual += 1;
          //funcionamento igual ao do método da esquerda para a direita, mudando apenas a lógica da posição da primeira letra.
          if (letraAtual == palavras[palavraAtual][0].length()) {
            palavras[palavraAtual][1] ="[" + lin + "," + (col + palavras[palavraAtual][0].length() - 1) + "]";
            for (int j = 0; j < palavras[palavraAtual][0].length(); j++) {
                mapaBoolean[lin][col + j] = true;
            }
            letraAtual = 0;
            break;
          }
        } else {
          letraAtual = 0;
        }
      }
    }
  }
 //método para procurar as palavras de cima para baixo
  private void cimaParaBaixo (int palavraAtual,int mapaQtdLinha,int mapaQtdColuna,char[][] mapa,String[][] palavras) {
    //percorre linhas de cima para baixo
    //agora começa por coluna
    int letraAtual = 0;
    for (int col = 0; col < mapaQtdColuna; col++) {
      for (int lin = 0; lin < mapaQtdLinha; lin++) {
         //melhorar desempenho, dar break quando não precisa mais procurar
         if (lin > (mapaQtdLinha - palavras[palavraAtual][0].length()) && letraAtual == 0) {
          break;
       } else if (mapa[lin][col] == palavras[palavraAtual][0].charAt(letraAtual)) {
          letraAtual += 1;
          
          if (letraAtual == palavras[palavraAtual][0].length()) {
            palavras[palavraAtual][1] = "[" + (lin - palavras[palavraAtual][0].length() + 1) + "," + col + "]";
            for (int j = 0; j < palavras[palavraAtual][0].length(); j++) {
                mapaBoolean[lin - j][col] = true;
            }
            letraAtual = 0;
            break;
          }
        } else {
          letraAtual = 0;
        }
      }
    }
  }
 //método para procurar as palavras de baixo para cima
  private void baixoParaCima (int palavraAtual,int mapaQtdLinha,int mapaQtdColuna,char[][] mapa,String[][] palavras) {
      //percorre linhas de baixo para cima
      //agora começa por coluna ao invés de linha
      int letraAtual = 0;
      for (int col = 0; col < mapaQtdColuna; col++) {
        for (int lin = (mapaQtdLinha - 1); lin >= 0; lin -= 1) {
          //melhorar desempenho, dar break quando não precisa mais procurar
          if (lin < (palavras[palavraAtual][0].length() - 1) && letraAtual == 0) {
            break;
          } else if (mapa[lin][col] == palavras[palavraAtual][0].charAt(letraAtual)) {
            letraAtual += 1;
          
            if (letraAtual == palavras[palavraAtual][0].length()) {
              palavras[palavraAtual][1] = "[" + (lin + palavras[palavraAtual][0].length() - 1) + "," + col + "]";
              for (int j = 0; j < palavras[palavraAtual][0].length(); j++) {
                mapaBoolean[lin + j][col] = true;
            }
              letraAtual = 0;
              break;
            }
          } else {
            letraAtual = 0;
          }
        }
      }
  }
 //método para procurar as palavras da esquerda para a direita, e depois na diagonal esquerda acima
  private void diagonalEsquerdaAcima(int palavraAtual,int mapaQtdLinha,int mapaQtdColuna,char[][] mapa,String[][] palavras) {
    // procura da esquerda para a direita, e depois compara na diagonal esquerda acima
    int auxiliar = 0;
    int letraAtual = 0;
    for (int lin = 0; lin < mapaQtdLinha; lin++) {
      for (int col = 0; col < mapaQtdColuna; col++) {
      //melhora desempenho, dá break quando a coluna e linha já passou do tamanho da palavra.
        if (lin < (palavras[palavraAtual][0].length() - 1) && col < (palavras[palavraAtual][0].length() - 1)) {
          break;
         } else if (lin >= (palavras[palavraAtual][0].length() - 1) &&
                    col >= (palavras[palavraAtual][0].length() - 1)) {

          if ((mapa[lin][col] == palavras[palavraAtual][0].charAt(letraAtual))) {
            letraAtual += 1;
            for (auxiliar = 1; auxiliar < palavras[palavraAtual][0].length(); auxiliar += 1) {
              //aqui está o segredo pra fazer a comparação na diagonal, através de um FOR e uma variável auxiliar.
              if (mapa[lin - auxiliar][col - auxiliar] == palavras[palavraAtual][0].charAt(letraAtual)) {
                letraAtual += 1;
                if (letraAtual == palavras[palavraAtual][0].length()) {
                  palavras[palavraAtual][1] = "[" + lin + "," + col + "]";
                  for (int j = 0; j < palavras[palavraAtual][0].length(); j++) {
                    mapaBoolean[lin - j][col - j] = true;
                }
                  letraAtual = 0;
                  break;
                }
              } else {
                letraAtual = 0;
              }
            }
          }
        } else {
          letraAtual = 0;
        }
      }
    }
  }
//método para procurar as palavras da esquerda para a direita, e depois na diagonal direita acima
  private void diagonalDireitaAcima(int palavraAtual, int mapaQtdLinha,int mapaQtdColuna,char[][] mapa,String[][] palavras) {
    // procura da esquerda para a direita, e depois compara na diagonal direita acima
    int auxiliar = 0;
    int letraAtual = 0;
    for (int lin = 0; lin < mapaQtdLinha; lin++) {
      for (int col = 0; col < mapaQtdColuna; col++) {
        if ( lin < (palavras[palavraAtual][0].length() - 1) && col > (mapaQtdColuna - palavras[palavraAtual][0].length())) {
          break;
        } else if (lin >= (palavras[palavraAtual][0].length() - 1) && col <= (mapaQtdColuna - palavras[palavraAtual][0].length())) {
          if ((mapa[lin][col] == palavras[palavraAtual][0].charAt(letraAtual))) {
            letraAtual += 1;
            for (auxiliar = 1; auxiliar < palavras[palavraAtual][0].length(); auxiliar += 1) {
              //aqui a comparação é igual no método diagonal esquerda acima, mudando apenas o sinal, pois agora sobe a linha e aumenta a coluna.
              if (mapa[lin - auxiliar][col + auxiliar] == palavras[palavraAtual][0].charAt(letraAtual)) {
                letraAtual += 1;
                
                if (letraAtual == palavras[palavraAtual][0].length()) {
                  palavras[palavraAtual][1] = "[" + lin + "," + col + "]";
                  for (int j = 0; j < palavras[palavraAtual][0].length(); j++) {
                    mapaBoolean[lin - j][col + j] = true;
                }
                  letraAtual = 0;
                  break;
                }
              } else {
                letraAtual = 0;
              }
            }
          }
        } else {
          letraAtual = 0;
        }
      }
    }
  }
//método para procurar as palavras da esquerda para a direita, e depois na diagonal esquerda abaixo
  private void diagonalEsquerdaAbaixo(int palavraAtual,int mapaQtdLinha,int mapaQtdColuna,char[][] mapa,String[][] palavras) {
    // procura da esquerda para a direita, e depois compara na diagonal esquerda abaixo
    int auxiliar = 0;
    int letraAtual = 0;
    for (int lin = 0; lin < mapaQtdLinha; lin++) {
      for (int col = 0; col < mapaQtdColuna; col++) {
        if (lin > (mapaQtdLinha - palavras[palavraAtual][0].length()) &&col < (palavras[palavraAtual][0].length() - 1)) {
          break;
        } else if (lin <= (mapaQtdLinha - palavras[palavraAtual][0].length()) && col >= (palavras[palavraAtual][0].length() - 1)) {

          if ((mapa[lin][col] == palavras[palavraAtual][0].charAt(letraAtual))) {
            letraAtual += 1;
            for (auxiliar = 1; auxiliar < palavras[palavraAtual][0].length(); auxiliar += 1) {
              if (mapa[lin + auxiliar][col - auxiliar] == palavras[palavraAtual][0].charAt(letraAtual)) {
                letraAtual += 1;

                if (letraAtual == palavras[palavraAtual][0].length()) {
                  palavras[palavraAtual][1] = "[" + lin + "," + col + "]"; 
                  for (int j = 0; j < palavras[palavraAtual][0].length(); j++) {
                    mapaBoolean[lin + j][col - j] = true;
                }   
                  letraAtual = 0;
                  break;
                }
              } else {
                letraAtual = 0;
              }
            }
          }
        } else {
          letraAtual = 0;
        }
      }
    }
  }
//método para procurar as palavras da esquerda para a direita, e depois na diagonal direita abaixo
  private void diagonalDireitaAbaixo(int palavraAtual,int mapaQtdLinha,int mapaQtdColuna,char[][] mapa,String[][] palavras) {
    // procura da esquerda para a direita, e depois compara na diagonal direita abaixo
    int auxiliar = 0;
    int letraAtual = 0;
    for (int lin = 0; lin < mapaQtdLinha; lin++) {
      for (int col = 0; col < mapaQtdColuna; col++) {
        if (lin > (mapaQtdLinha - palavras[palavraAtual][0].length()) && col > (mapaQtdColuna - palavras[palavraAtual][0].length())) {
          break;
        } else if (lin <= (mapaQtdLinha - palavras[palavraAtual][0].length()) && col <= (mapaQtdColuna - palavras[palavraAtual][0].length())) {
          if ((mapa[lin][col] == palavras[palavraAtual][0].charAt(letraAtual))) {
            letraAtual += 1;
            for (auxiliar = 1; auxiliar < palavras[palavraAtual][0].length(); auxiliar += 1) {
              if (mapa[lin + auxiliar][col + auxiliar] == palavras[palavraAtual][0].charAt(letraAtual)) {
                letraAtual += 1;
                if (letraAtual == palavras[palavraAtual][0].length()) {
                  palavras[palavraAtual][1] = "[" + lin + "," + col + "]";
                  for (int j = 0; j < palavras[palavraAtual][0].length(); j++) {
                    mapaBoolean[lin + j][col + j] = true;
                }
                  letraAtual = 0;
                  break;
                }
              } else {
                letraAtual = 0;
              }
            }
          }
        } else {
          letraAtual = 0;
        }
      }
    }
  }
  //chamando o método mapa pesquisa, dentro dele, será executado os 8 métodos de procurar palavras
  public void mapaPesquisar(int mapaQtdLinha, int mapaQtdColuna,char[][] mapa, String[][] palavras) {
    for (int palavraAtual = 0; palavraAtual < palavras.length; palavraAtual++) {
      
      //IF para melhorar desempenho do código, se a coluna 1 da matriz palavras estiver vazia, a palavra ainda não foi encontrada
      //Se a palavra foi encontrada em um dos métodos, para de procurar nos outros imediatamente e vai pra próxima palavra.
      
      //esse primeiro método não precisa do IF pois sempre começamos procurando por ele.
      esquerdaParaDireita(palavraAtual,mapaQtdLinha, mapaQtdColuna, mapa, palavras);

      if (palavras[palavraAtual][1] == null) {
      direitaParaEsquerda(palavraAtual, mapaQtdLinha, mapaQtdColuna, mapa, palavras);
    }
      if (palavras[palavraAtual][1] == null) { 
      cimaParaBaixo(palavraAtual, mapaQtdLinha, mapaQtdColuna, mapa, palavras);
    }
      if (palavras[palavraAtual][1] == null) {
      baixoParaCima(palavraAtual, mapaQtdLinha, mapaQtdColuna, mapa, palavras);
    }
      if (palavras[palavraAtual][1] == null) {
      diagonalEsquerdaAcima(palavraAtual,mapaQtdLinha,mapaQtdColuna,mapa,palavras);
    }
      if (palavras[palavraAtual][1] == null) {
      diagonalDireitaAcima(palavraAtual,mapaQtdLinha,mapaQtdColuna,mapa,palavras);
    }
      if (palavras[palavraAtual][1] == null) {
      diagonalEsquerdaAbaixo(palavraAtual,mapaQtdLinha,mapaQtdColuna,mapa,palavras);
    }
      if (palavras[palavraAtual][1] == null) {
      diagonalDireitaAbaixo(palavraAtual,mapaQtdLinha,mapaQtdColuna,mapa,palavras);
    }
    }
  }

  public static void main(String[] args) throws Exception {
    //chamando o método construtor no método Main
    new CacaPalavrasCompleto();
  }
}
