//KALIEL KAUTNICK

public class Conta {

  private double saldo;
  private String numero;

  //CONSTRUTOR VAZIO
  public Conta() {}

  //CONSTRUTOR CHEIO, EXCETO PELO SALDO QUE COMEÇA COM 0
  public Conta(String numero) {
    this.saldo = 0.0;
    this.numero = numero;
  }

  //GETTERS E SETTERS DE TODOS OS ATRIBUTOS DA CLASSE CONTA:
  public double getSaldo() {
    return this.saldo;
  }

  //O SET SALDO NÃO IRÁ EXISTIR POIS ELE SERÁ CALCULADO;

  public String getNumero() {
    return this.numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  //AQUI ACABA OS MÉTODOS GETTERS E SETTERS:

  //MÉTODO DE SACAR DINHEIRO, QUE TIRA O DINHEIRO DA CONTA
  public void sacar(double valor) {
    //A CONTA SÓ VAI SACAR SE O VALOR A SACAR NÃO FOR MAIR QUE O SALDO DA CONTA.
    if (this.saldo >= valor && valor > 0.0) {
      this.saldo -= valor;
    } else if (valor < 0) {
      throw new IllegalArgumentException(
        "Valor da operação não pode ser negativo."
      );
    } else {
      throw new IllegalArgumentException(
        "Você não possui saldo suficiente para efetuar a operação."
      );
    }
  }

  //MÉTODO DE DEPOSITAR NA CONTA, QUE INCREMENTA O DINHEIRO NA CONTA
  public void depositar(double valor) {
    //DEPOSITOS SEM LIMITES.
    if (valor > 0.0) {
      this.saldo += valor;
    } else {
      throw new IllegalArgumentException(
        "Valor da operação não pode ser negativo."
      );
    }
  }
}
