package Teste;

import java.util.Scanner;

public class Programa {
    private String[][] tabuleiro;
    private int largura;
    private int altura;
    private int jogadorAtual;

    public Programa() {
        tabuleiro = new String[7][7];
        jogadorAtual = 0;
        largura = 7;
        altura = 7;
        Scanner sc = new Scanner(System.in);
        while (!tabuleiroCheio() && !liga4()) {
            System.out.println("Informe a coluna a jogar: (0 até 7): ");
            int coluna = sc.nextInt();
            jogadorAtual = jogadorAtual == 0 ? 1 : 0;
            inserePeca(coluna, jogadorAtual == 0 ? "O" : "X");
            imprimeTabuleiro();
        }
        if (tabuleiroCheio()) {
            System.out.println("Tabuleiro encheu");
        }
        System.out.println("Jogador " + jogadorAtual + " ganhou!");
        sc.close();
    }

    public boolean tabuleiroCheio() {
        // para verificar se está cheio, basta percorrer a linha de cima, se tiver
        // alguma posição null,
        // então o tabuleiro ainda não está cheio
        for (int i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[0][i] == null) {
                return false;
            }
        }
        return true;
    }

    public boolean liga4Vertical() {
        int qtdeRepeticoesX = 0;
        int qtdeRepeticoesO = 0;
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] != null) {
                    if (tabuleiro[i][j].equals("X")) {
                        qtdeRepeticoesX++;
                        qtdeRepeticoesO = 0;
                    } else if (tabuleiro[i][j].equals("O")) {
                        qtdeRepeticoesO++;
                        qtdeRepeticoesX = 0;
                    }
                }
                if (qtdeRepeticoesO >= 4 || qtdeRepeticoesX >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean liga4Horizontal() {
        int qtdeRepeticoesX = 0;
        int qtdeRepeticoesO = 0;
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[j][i] != null) {
                    if (tabuleiro[j][i].equals("X")) {
                        qtdeRepeticoesX++;
                        qtdeRepeticoesO = 0;
                    } else if (tabuleiro[j][i].equals("O")) {
                        qtdeRepeticoesO++;
                        qtdeRepeticoesX = 0;
                    }
                }
                if (qtdeRepeticoesO >= 4 || qtdeRepeticoesX >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean liga4DiagonalEsquerda() {
        int qtdeRepeticoesX = 0;
        int qtdeRepeticoesO = 0;
        for (int i = 0; i < (altura - 4); i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] != null) {
                    if (tabuleiro[i][j].equals("X")) {
                        qtdeRepeticoesO = 0;
                        for (int k = 0; k < (altura - 1); k++) {
                            if (tabuleiro[i + k][j - k].equals("X")) {
                                qtdeRepeticoesX++;
                            } else {
                                qtdeRepeticoesX = 0;
                                break;
                            }

                            if (qtdeRepeticoesX == 4) {
                                return true;
                            }
                        }

                    } else if (tabuleiro[i][j].equals("O")) {
                        qtdeRepeticoesX = 0;
                        for (int k = 0; k < (altura - 1); k++) {
                            if (tabuleiro[i + k][j - k].equals("O")) {
                                qtdeRepeticoesO++;
                            } else {
                                qtdeRepeticoesO = 0;
                                break;
                            }

                            if (qtdeRepeticoesO == 4) {
                                return true;
                            }
                        }
                    }
                }
                if (qtdeRepeticoesO >= 4 || qtdeRepeticoesX >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean liga4DiagonalDireita() {
        int qtdeRepeticoesX = 0;
        int qtdeRepeticoesO = 0;
        for (int i = 0; i < (altura - 4); i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] != null) {
                    if (tabuleiro[i][j].equals("X")) {
                        qtdeRepeticoesO = 0;
                        for (int k = 0; k < (altura - 1); k++) {
                            if (tabuleiro[i + k][j + k].equals("X")) {
                                qtdeRepeticoesX++;
                            } else {
                                qtdeRepeticoesX = 0;
                                break;
                            }

                            if (qtdeRepeticoesX == 4) {
                                return true;
                            }
                        }

                    } else if (tabuleiro[i][j].equals("O")) {
                        qtdeRepeticoesX = 0;
                        for (int k = 0; k < (altura - 1); k++) {
                            if (tabuleiro[i + k][j + k].equals("O")) {
                                qtdeRepeticoesO++;
                            } else {
                                qtdeRepeticoesO = 0;
                                break;
                            }

                            if (qtdeRepeticoesO == 4) {
                                return true;
                            }
                        }
                    }
                }
                if (qtdeRepeticoesO >= 4 || qtdeRepeticoesX >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean liga4() {
        if (liga4Horizontal()) {
            return true;
        } else if (liga4Vertical()) {
            return true;
        } else if (liga4DiagonalEsquerda()) {
            return true;
        } else if (liga4DiagonalDireita()) {
            return true;
        }
        return false;
    }

    public void imprimeTabuleiro() {
        System.out.println("-------------------------------");
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] == null) {
                    System.out.print("  | ");
                } else if (tabuleiro[i][j].equals("X")) {
                    System.out.print(tabuleiro[i][j] + " | ");
                } else {
                    System.out.print(tabuleiro[i][j] + " | ");
                }
            }
            System.out.println("  |");
            System.out.println("-------------------------------");
        }
    }

    public boolean colunaEstaCheia(int coluna) {
        // percorre a coluna especificada de cima pra baixo, se tudo estiver diferente
        // de null
        // nessa coluna, retorna verdadeiro
        for (int i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[i][coluna] == null) {
                return false;
            }
        }
        return true;
    }

    public void inserePeca(int coluna, String peca) {
        if (!colunaEstaCheia(coluna)) {
            {
                // aqui percorre o vetor de baixo para cima
                for (int i = altura - 1; i >= 0; i--) {
                    if (tabuleiro[i][coluna] == null) {
                        // se naquela linha e coluna estiver nulo, então insere ali
                        tabuleiro[i][coluna] = peca;
                        break;
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Programa p = new Programa();
    }
}
