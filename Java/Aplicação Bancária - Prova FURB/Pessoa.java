//KALIEL KAUTNICK

import java.util.ArrayList;

public class Pessoa {

  private String codigo;
  private String nome;
  public ArrayList<Conta> contas = new ArrayList<>();

  //CONSTRUTOR VAZIO
  public Pessoa() {}

  //CONSTRUTOR COM ARGUMENTOS
  public Pessoa(String codigo, String nome) {
    super();
    this.codigo = codigo;
    this.nome = nome;
  }

  //GETTERS E SETTERS:
  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public ArrayList<Conta> getContas() {
    return this.contas;
  }

  //MÉTODO DE ADICIONAR NOVA CONTA PARA O CLIENTE, RECEBE POR PARAMETRO A CONTA A SER ADICIONADA
  public void criarConta(Conta conta) {
    this.contas.add(conta);
  }

  //MÉTODO PARA RETORNAR O SALDO TOTAL DO CLIENTE, PERCORRENDO O SALDO DE TODAS AS CONTAS;
  public double getSaldoGeralCliente() {
    double somador = 0.0;

    for (Conta c : contas) {
      if (c != null) {
        somador += c.getSaldo();
      }
      //ele vai somando o saldo de todas as contas que não são nulas, e depois ele retorna o somador.
    }
    return somador;
  }

  public void sacar(String numero, double valor) {
    for (Conta c : contas) {
      if (c.getNumero().equals(numero)) {
        c.sacar(valor);
      }
    }
  }

  public void depositar(String numero, double valor) {
    for (Conta c : contas) {
      if (c.getNumero().equals(numero)) {
        c.depositar(valor);
      }
    }
  }

  //MÉTODO ESPECIAL DE SAQUE ESPECIAL, ONDE PODE SER TIRADO O SALDO DE TODAS AS CONTAS SE NECESSÁRIO FOR
  //INDEPENDENTE DA CONTA, PARA QUE POSSA ATENDER AO SAQUE QUE ELE QUER FAZER
  public void saqueEspecialCliente(double valor) {
    //O SALDO AUXILIAR, VAI ME AJUDAR A SABER O QUANTO VAI SOBRANDO DE CADA SAQUE EU VOU FAZENDO NAS
    //CONTAS..SEM ELE SERIA MAIS DIFICIL DE CONCRETIZAR O MÉTODO
    double saldoAuxiliar = valor;

    //JÁ LOGO DE CARA EU COMPARO SE EU VOU CONSEGUIR ATENDER AO VALOR QUE PRECISA SER SACADO
    //SE O SOMATÓRIO DE TODAS AS MINHAS CONTAS FOR MENOR OU IGUAL AO VALOR EXIGIDO, SEI QUE
    //POSSO RETIRAR O VALOR DE TODAS ELAS E ALGUMA HORA IRÁ ATENDER AO VALOR ESPERADO
    if (getSaldoGeralCliente() >= valor && valor > 0.0) {
      for (Conta c : contas) {
        if (c != null && saldoAuxiliar > 0) {
          //MINHA VARIAVEL DE VALOR A SACAR IRÁ RECEBER O SALDO DESSA CONTA INICIALIZADA
          double valorSacar = c.getSaldo();
          //SE O MEU SALDO AUXILIAR - QUANTIDADE A SACAR FOR MAIOR QUE 0, POSSO SACAR TUDO
          if (saldoAuxiliar - c.getSaldo() > 0) {
            c.sacar(valorSacar);
            saldoAuxiliar -= valorSacar;
            //POR OUTRO LADO, SE MEU SALDO AUXILIAR FOR MENOR OU IGUAL AO SALDO DA CONTA
            //EU VOU ATRIBUIR O VALOR A SACAR DA CONTA, IGUAL AO SALDO QUE RESTA
            //E ENTÃO É ESSE VALOR QUE IREI SACAR DA CONTA
          } else if (saldoAuxiliar <= c.getSaldo()) {
            valorSacar = saldoAuxiliar;
            c.sacar(valorSacar);
            saldoAuxiliar -= valorSacar;
          }
        }
      }
    }
  }

  public void transferir(
    String numContaSacar,
    String numContaDepositar,
    double valor
  ) {
    boolean sacou = false;

    //AQUI EU VOU PERCORRER O ARRAY LIST QUE EU CRIEI DE CONTAS, PEGANDO AS CONTAS QUE
    //TEM NA CLASSE PESSOA, E IREI PERCORRER TODAS ELAS, COMPARANDO O NUMERO;
    //QUANDO ACHAR O NUMERO DA CONTA A TRANSFERIR, EU DEPOSITO O VALOR DELA;
    //QUANDO ACHAR O NUMERO DA CONTA A SER TRANSFERIDA, EU SACO O VALOR NELA
    for (Conta c : contas) {
      //PRIMEIRO VALIDA SE EXISTE DINHEIRO SUFICIENTE PARA TRANSFERIR
      if (c.getSaldo() >= valor) {
        //SACA DA CONTA QUE VAI TRANSFERIR O DINHEIRO
        if (c.getNumero().equals(numContaSacar)) {
          c.sacar(valor);
          sacou = true;
        }
      }
    }

    for (Conta c2 : contas) {
      //DEPOSITA NA CONTA QUE VAI RECEBER O DINHEIRO
      if (c2.getNumero().equals(numContaDepositar) && sacou == true) {
        c2.depositar(valor);
      }
    }
  }

  //MÉTODO DE RETORNAR O SALDO DA CONTA, COM O NUMERO DELA COMO PARAMETRO:
  public double getSaldoConta(String numeroConta) {
    double saldoConta = 0.0;
    for (Conta c : contas) {
      if (c.getNumero().equals(numeroConta)) {
        saldoConta = c.getSaldo();
      }
    }
    return saldoConta;
  }
}
