package Teste;

import java.util.Scanner;

public class JogoLiga4 {

    public JogoLiga4() {
        String[][] tabuleiro = new String[6][7];
        String jogadorAtual = "X";
        int largura = 7;
        int altura = 6;
        int coluna = 0;
        iniciaTabuleiro(tabuleiro, largura, altura);
        Scanner sc = new Scanner(System.in);
        while (!tabuleiroCheio(tabuleiro) && !liga4(tabuleiro, jogadorAtual, largura, altura)) {
            jogadorAtual = jogadorAtual == "X" ? "O" : "X";
             if (jogadorAtual == "X") {
                System.out.println("Informe a coluna a jogar: (0 até 6): ");
                coluna = sc.nextInt();  
                    while (colunaEstaCheia(tabuleiro, coluna) || 
                                           coluna > 6 || coluna < 0) {
                      System.out.println("Coluna " + coluna + 
                                  " está indisponível, favor escolher outra");
                      coluna = sc.nextInt();
                    }
                } else {
                    coluna = (int)((Math.random() * 5) - 0.01) + 1;
                          while (colunaEstaCheia(tabuleiro, coluna) || 
                                             coluna > 6 || coluna < 0) {
                                coluna = (int)((Math.random() * 5) - 0.01) + 1;
                            }
                    }
            inserePeca(tabuleiro, altura, coluna, jogadorAtual);
            imprimeTabuleiro(tabuleiro);
        }
        if (tabuleiroCheio(tabuleiro)) {
            System.out.println("Tabuleiro encheu");
        } else {
        System.out.println("Jogador " + jogadorAtual + " ganhou!");
        }
        sc.close();
    }

    public void iniciaTabuleiro(String [][]tabuleiro, int largura, int altura) {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                tabuleiro[i][j] = "a";
            }
        }
    }

    public boolean tabuleiroCheio(String [][]tabuleiro) {
        // para verificar se está cheio, basta percorrer a linha de cima, se tiver
        // alguma posição null,
        // então o tabuleiro ainda não está cheio
        for (int i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[0][i] == "a") {
                return false;
            }
        }
        return true;
    }

    public boolean liga4Vertical(String [][]tabuleiro, String jogadorAtual, int largura, int altura) {
        int qtdeRepeticoesX;
        for (int i = 0; i < altura; i++) {
        qtdeRepeticoesX = 0;
            for (int j = 0; j < largura; j++) {
                    if (tabuleiro[i][j] == jogadorAtual) {
                        qtdeRepeticoesX++;
                    } else {
                  qtdeRepeticoesX = 0;
                }
                if (qtdeRepeticoesX >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean liga4Horizontal(String [][]tabuleiro, String jogadorAtual, int largura, int altura) {
        int qtdeRepeticoesX;
        for (int i = 0; i < largura; i++) {
        qtdeRepeticoesX = 0;
            for (int j = 0; j < altura; j++) {
                    if (tabuleiro[j][i] == jogadorAtual) {
                        qtdeRepeticoesX++;
                    } else {
                  qtdeRepeticoesX = 0;
                }
                if (qtdeRepeticoesX >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean liga4DiagonalEsquerda(String [][]tabuleiro, String jogadorAtual, int largura, int altura) {
        int qtdeRepeticoesX;
        for (int i = 0; i < (altura - 3); i++) {
        qtdeRepeticoesX = 0;
            for (int j = 3; j < (largura - 1); j++) {
                if (tabuleiro[i][j] == jogadorAtual) {
                    for (int k = 0; k < 4; k++) {
                        if (tabuleiro[i + k][j - k] != null) {
                            if (tabuleiro[i + k][j - k] == jogadorAtual) {
                            qtdeRepeticoesX++;
                            } else {
                                qtdeRepeticoesX = 0;
                                break;
                            }

                            if (qtdeRepeticoesX == 4) {
                                return true;
                            }
                        }
                    } 
                } else {
                   qtdeRepeticoesX = 0;
                }
                if (qtdeRepeticoesX >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean liga4DiagonalDireita(String [][]tabuleiro, String jogadorAtual, int largura, int altura) {
        int qtdeRepeticoesX;
         for (int i = 0; i < (altura - 3); i++) {
        qtdeRepeticoesX = 0;
            for (int j = 0; j < (largura - 3); j++) {
                    if (tabuleiro[i][j] == jogadorAtual) {
                        for (int k = 0; k < 4; k++) {
                            if (tabuleiro[i + k][j + k] != null) {
                               if (tabuleiro[i + k][j + k] == jogadorAtual) {
                                   qtdeRepeticoesX++;
                                } else {
                                qtdeRepeticoesX = 0;
                                break;
                            }

                            if (qtdeRepeticoesX == 4) {
                                return true;
                            }
                        }
                    } 
                } else {
                   qtdeRepeticoesX = 0;
                }
                if (qtdeRepeticoesX >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean liga4(String [][]tabuleiro, String jogadorAtual, int largura, int altura) {
        if (liga4Horizontal(tabuleiro, jogadorAtual, largura, altura)) {
            return true;
        } else if (liga4Vertical(tabuleiro, jogadorAtual, largura, altura)) {
            return true;
        } else if (liga4DiagonalEsquerda(tabuleiro, jogadorAtual, largura, altura)) {
            return true;
        } else if (liga4DiagonalDireita(tabuleiro, jogadorAtual, largura, altura)) {
            return true;
        }
        return false;
    }

    public void imprimeTabuleiro(String [][]tabuleiro) {
        System.out.println("-----------------------------");
        for (int i = 0; i < tabuleiro.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] == "a") {
                    System.out.print("  | ");
                } else if (tabuleiro[i][j] == "X") {
                    System.out.print(tabuleiro[i][j] + " | ");
                } else {
                    System.out.print(tabuleiro[i][j] + " | ");
                }
            }
            System.out.println("");
            System.out.println("-----------------------------");
        }
    }

    public boolean colunaEstaCheia(String [][]tabuleiro, int coluna) {
        // percorre a coluna especificada de cima pra baixo, se tudo estiver diferente
        // de null
        // nessa coluna, retorna verdadeiro
        if (coluna > 6 || coluna < 0) {
            return false;
        }
        for (int i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[i][coluna] == "a") {
                return false;
            }
        }
        return true;
    }

    public void inserePeca(String [][]tabuleiro, int altura, int coluna, String peca) {
        if (!colunaEstaCheia(tabuleiro,coluna)) {
            {
                // aqui percorre o vetor de baixo para cima
                for (int i = altura - 1; i >= 0; i--) {
                    if (tabuleiro[i][coluna] == "a") {
                        // se naquela linha e coluna estiver nulo, então insere ali
                        tabuleiro[i][coluna] = peca;
                        break;
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        JogoLiga4 p = new JogoLiga4();
    }
}
