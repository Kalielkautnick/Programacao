//O CÓDIGO A BAIXO, EU PEGO UM ARRAY, ORDENO ELE EM ORDEM CRESCENTE, E DEPOIS COLOCO TODOS OS ELEMENTOS EM ORDEM DECRESCENTE
import java.util.Arrays;

public class ArrayInvertido {
  
    public static int inverteVetor(int [] vetor) {
        //esse método inverte todos os elementos do vetor em ordem descrecente, deve-se rodar 2x para voltar ao normal.
         for (int i = 0; i < vetor.length / 2; i++) {
            int temp = vetor[i];
            vetor[i] = vetor[vetor.length - 1 - i];
            vetor[vetor.length - 1 - i] = temp;
        } 
        return vetor;
    }
  
    public static void main(String[] args) {
        int[] numeros = { 30, 50, 10, 40, 20 };
        Arrays.sort(numeros);
        //aqui o vetor fica: 10, 20, 30, 40, 50
        int[] numerosDecrescente = inverteVetor(numeros);
        //agora o vetor numerosDecrescente fica:  50, 40, 30, 20, 10
    }
}
