import java.io.*;

public class ajustaQuebraDeLinha {
    public static void main(String[] args) {
        // Caminho do arquivo .txt
        String caminhoArquivo = "S:\\Importacao\\Programação\\avaliar arquivos aqui\\arquivo.txt";
        // Caminho do arquivo de saída
        String caminhoArquivoSaida = "S:\\Importacao\\Programação\\avaliar arquivos aqui\\arquivoCorrigido.txt";
        // Palavra que deve iniciar cada linha
        String palavraChave = "C\t";
        int contadorErros = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
            BufferedReader gambi = new BufferedReader(new FileReader(caminhoArquivo));
             BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivoSaida))) {

            String linha;
            String linha_posterior;
            int numeroLinha = 1;

            linha_posterior = gambi.readLine();
            while ((linha = br.readLine()) != null) {
                linha_posterior = gambi.readLine();
                    if (!linha_posterior.startsWith(palavraChave)) {
                        contadorErros += 1;
                        System.out.println("Linha " + numeroLinha + " não começa com: '" + palavraChave + "'");                    
                        bw.write(linha);
                    }
                    else {
                        bw.write(linha);
                        bw.newLine();
                }   
                numeroLinha++;              
            }

            System.out.println("Verificação do arquivo concluída.\nErros: " + contadorErros);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
