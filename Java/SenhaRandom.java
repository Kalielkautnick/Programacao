import java.util.Random;
import java.util.Scanner;

public class SenhaRandom {

  private String numeros[] = {
    "0",
    "1",
    "2",
    "3",
    "4",
    "5",
    "6",
    "7",
    "8",
    "9",
  };
  private String letras[] = new String[26];
  private String alfabeto = "ABCDEFDHIJKLMNOPQRSTUVWXYZ";
  private String especiais[] = new String[20];
  private String caracteres = "[]{;&%$#@+?.,*)(:~Ç!";
  private int qtNumeros = 0;
  private int qtLetras = 0;
  private int qtEspeciais = 0;

  public SenhaRandom(int qtdeNumeros, int qtdeLetras, int qtdeEspeciais) {
    for (int i = 0; i < letras.length; i++) {
      letras[i] = alfabeto.substring(i, i + 1);
    }
    for (int i = 0; i < especiais.length; i++) {
      especiais[i] = caracteres.substring(i, i + 1);
    }
    this.qtNumeros = qtdeNumeros;
    this.qtLetras = qtdeLetras;
    this.qtEspeciais = qtdeEspeciais;
  }

  public String randomLetra(Random r) {
    String s = letras[r.nextInt(26)];
    if (r.nextBoolean()) {
      s = s.toLowerCase();
    }
    this.qtLetras--;
    return s;
  }

  public String randomNumero(Random r) {
    this.qtNumeros--;
    return numeros[r.nextInt(10)];
  }

  public String randomEspecial(Random r) {
    String s = especiais[r.nextInt(20)];
    if (r.nextBoolean()) {
      s.toLowerCase();
    }
    this.qtEspeciais--;
    return s;
  }

  public String geraSenha() {
    String randomKey = "";
    Random r = new Random();
    while (this.qtNumeros > 0 || this.qtLetras > 0 || this.qtEspeciais > 0) {
      int option = r.nextInt(4) + 1;
      switch (option) {
        case 1:
          if (this.qtNumeros > 0) {
            randomKey += randomNumero(r);
          } else if (this.qtLetras > 0) {
            randomKey += randomLetra(r);
          } else {
            randomKey += randomEspecial(r);
          }
          break;
        case 2:
          if (this.qtLetras > 0) {
            randomKey += randomLetra(r);
          } else if (this.qtEspeciais > 0) {
            randomKey += randomEspecial(r);
          } else {
            randomKey += randomNumero(r);
          }
          break;
        case 3:
          if (this.qtEspeciais > 0) {
            randomKey += randomEspecial(r);
          } else if (this.qtNumeros > 0) {
            randomKey += randomNumero(r);
          } else {
            randomKey += randomLetra(r);
          }
          break;
      }
    }

    return randomKey;
  }

  public static void main(String[] args) {
    int numeros = 0;
    int letras = 0;
    int especiais = 0;
    try {
      Scanner sc = new Scanner(System.in);
      System.out.println("Quantidade de números na senha: ");
      numeros = sc.nextInt();
      System.out.println("Quantidade de letras na senha: ");
      letras = sc.nextInt();
      System.out.println("Quantidade de caracters especiais na senha:");
      especiais = sc.nextInt();
      SenhaRandom s = new SenhaRandom(numeros, letras, especiais);
      SenhaRandom s1 = new SenhaRandom(numeros, letras, especiais);
      SenhaRandom s2 = new SenhaRandom(numeros, letras, especiais);
      System.out.println(
        "\nNova senha:\nSenha 1: " +
        s.geraSenha() +
        "\nSenha 2: " +
        s1.geraSenha() +
        "\nSenha 3: " +
        s2.geraSenha()
      );
      sc.close();
    } catch (Exception e) {
      System.out.println(
        "Somente é possível informar números. Execute o programa novamente."
      );
    }
  }
}
