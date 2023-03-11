import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Telefone {

  String numero;
  LocalDate dataInstalacao;
  Double custo = 0.0;
  boolean internet = false;
  String ramoAtividade;
  int qtdeOcorrencias = 0;
  char tipo;
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public Telefone() {}

  //CONSTRUTOR PARA TELEFONE RESIDENCIAL
  public Telefone(String numero,LocalDate dataInstalacao, boolean internet) {
    String aux = numero.replaceAll("[\\D]", "");
    if (aux.length() == 10) {
      this.numero = aux;
    }
    this.dataInstalacao = dataInstalacao;
    this.internet = internet;
    this.tipo = 'R';
    setCusto();
  }

  //CONSTRUTOR PARA TELEFONE COMERCIAL
  public Telefone(String numero,LocalDate dataInstalacao,String ramoAtividade) {
    String aux = numero.replaceAll("[\\D]", "");
    if (aux.length() == 10) {
        this.numero = aux;
    }
    this.dataInstalacao = dataInstalacao;
    if (ramoAtividade != null) {
    this.ramoAtividade = ramoAtividade;
    }
    this.tipo = 'C';
    setCusto();
  }

  //CONSTRUTOR PARA TELEFONE ESPECIALIZADO
  public Telefone(String numero,LocalDate dataInstalacao,int qtdeOcorrencias) {
    String aux = numero.replaceAll("[\\D]", "");
    if (aux.length() == 10) {
      this.numero = aux;
    }
    this.dataInstalacao = dataInstalacao;
    if (qtdeOcorrencias > 0) {
    this.qtdeOcorrencias = qtdeOcorrencias;
    }
    this.tipo = 'E';
    setCusto();
  }

  public String getNumero() {
    return numero;
  }

 
  public LocalDate getDataInstalacao() {
    return dataInstalacao;
  }

  public void setCusto() {
	  //AQUI INICIAMOS UMA DATA COM 01/01/2019 PARA NOS AUXILIAR A VALIDAR
	  //A DATA DE INSTALAÇÃO, SE ELA É MENOR, IGUAL OU MAIOR
    LocalDate data =  LocalDate.of(2019, 01, 01);

    if (this.getTipo() == 'R') {
    	this.custo = 15.0;
    } else if (this.getTipo() == 'C'  && this.getDataInstalacao().isBefore(data)) {
      this.custo = 25.0;
    } else if (this.getTipo() == 'C' && 
    		((this.getDataInstalacao().isAfter(data)) ||
    		this.getDataInstalacao().isEqual(data))) {
      this.custo = 37.5;
    } else if (this.getTipo() == 'E') {
      //CHAMA O MÉTODO QUE ESTÁ LOGO ABAIXO DESSE
      this.custo = (this.getCustoPorOcorrencias()); 
    }
  }

      public double getCusto() {
          return this.custo;
      }

  public double getCustoPorOcorrencias() {
    double valor = 0.0;
    if (this.getQtdeOcorrencias() > 0 && this.getQtdeOcorrencias() <= 1000) {
      valor = 50.0;
    } else if (this.getQtdeOcorrencias() <= 5000) {
      valor = 67.8;
    } else if (this.getQtdeOcorrencias() <= 10000) {
      valor = 98.5;
    } else if (this.getQtdeOcorrencias() <= 50000) {
      valor = 123.9;
    } else {
      valor = 187.82;
    }
    return valor;
  }

  public boolean temInternet() {
    return internet;
  }

  public String getRamoAtividade() {
    return ramoAtividade;
  }

  public int getQtdeOcorrencias() {
    return qtdeOcorrencias;
  }

  public char getTipo() {
        return this.tipo;
  }

}