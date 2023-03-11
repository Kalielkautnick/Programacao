import java.time.LocalDate;
import java.util.HashMap;

public class Cliente {
    private String nome;
    private String endereco;
    private HashMap<String, Telefone> telefones = new HashMap<>();
    
    public Cliente(String nome, String endereco) throws Exception {
    	
    	if(!nome.equals("") && !endereco.equals("")) {
    		this.nome = nome;
            this.endereco = endereco;
    	} else {
    		throw new Exception("Campos incompletos");
    	}    
    }
    
    public String getNome() {
        return nome;
    }
  
    public String getEndereco() {
        return endereco;
    }

    public double getCustoTelefones() {
        double somador = 0.0;
        for (Telefone t : telefones.values()) {
            if (t != null) {
                somador += t.getCusto();
            }
        }
        return somador;
    }

  //AQUI COMEÇA OS GETS DOS TELEFONES QUE O USUÁRIO VAI FAZER PELO NÚMERO.
  public double getCusto(String numero) {
    double valor = 0.0;
      valor = telefones.get(numero).getCusto();
        return valor;
  } 

  public char getTipo(String numero) {
    char s;
    		s = telefones.get(numero).getTipo();
    return s;
  }

  public String getRamoAtividade(String numero) {
    String s = "";
   if (telefones.get(numero).getTipo() == 'C') {
	   s = telefones.get(numero).getRamoAtividade();
   }
    return s;
  }

  public LocalDate getDataInstalacao(String numero) {
    LocalDate data = LocalDate.now();
            data = telefones.get(numero).getDataInstalacao();   
    return data; 
      } 

      public boolean temInternet(String numero) {   
             boolean b = false;
                if (telefones.get(numero).getTipo() == 'R') {
                	b = telefones.get(numero).temInternet();
                }
                return b;
            } 
    
          public int getQtdeOcorrencias(String numero) {   
            int qtde = 0;
            	if (telefones.get(numero).getTipo() == 'E') {
            		qtde = telefones.get(numero).getQtdeOcorrencias();
            	}
               return qtde;
            } 

            //ACABA AQUI AS CONSULTAS DO TELEFONE PELO NÚMERO.
  

    public void addTelefone(String numero, Telefone t) throws Exception{ 
        String aux = numero.replaceAll("[\\D]", "");
        if (aux.length() == 10 && t != null && !telefones.containsKey(aux)) {
        telefones.put(aux, t);
        } else {
        	throw new Exception("Numero já adicionado");
        }
    }
    
    public boolean validarInternet(String s) {
   	 return (s.trim().substring(0,1).toUpperCase().equals("S")) ? true : false; 
     }
    
}

