import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class GeraInsertFaixaIten {

    public static void main(String[] args) {

        String[] lista = new String [1000];

        //PARA FUNCIONAR, BASTA APENAS COLAR AQUI DENTRO O RESULTADO DA QUERY DENTRO DESSE TRECHO DO CÓDIGO, O RESTO FUNCIONARÁ SOZINHO.
/*COMEÇA AQUI */
int max_faixa = 100;
lista[0] = "G;GG;U;XGG";
lista[1] = "G1;G2;G3;G4;G5;G6";
lista[2] = "G1;G2;G3;12;14;16";
lista[3] = "G4;G5;G6";
lista[4] = "M;GG";
lista[5] = "P;M;G;GG;G1";
lista[6] = "P;M;G;GG;G1;G2;G3";
lista[7] = "PP;P;M;G;GG;EGG;G1;G2;G3;EPP";
lista[8] = "PP;P;M;G;GG;EGG;U";
lista[9] = "PP;P;M;G;GG;EGG;38;40;42;44;36;46";
lista[10] = "PP;P;M;G;GG;U;38;40;42;44;XG;36;46";
lista[11] = "PP;P;M;G;GG;XGG;42;44;46;48";
lista[12] = "PP;P;M;G;GG;38;40;42;44;XG;36;46";
lista[13] = "U;1;2;3";
lista[14] = "U;4;6;8;10";
lista[15] = "0;G1;G2;G3";
lista[16] = "0;P;M;G;GG";
lista[17] = "0;P;M;G;GG;XGG";
lista[18] = "0;PP;P;M;G;GG;EGG;G1;G2;G3;EPP";
lista[19] = "1;2;3;4;6;8;10";
lista[20] = "4;6;8;10;12;14;16;18";
/*TERMINA AQUI */
        // FIM DO BLOCO DE VARIÁVEIS;

        String query_insert = "";
        String query_insert_faixa = "";

        String split_tamanhos [] = new String[100];
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null) {
                split_tamanhos = lista[i].split(";");

                for (int j = 0; j < split_tamanhos.length; j++) {
                    query_insert += "INSERT INTO FAIXA_ITEN_001 (FAIXA, TAMANHO, POSICAO, PERCENTUAL) VALUES (";
                    query_insert += "\'" + (max_faixa + i) + "\', \'" + split_tamanhos[j] + "\', " + (j+1) + ", 0);";
                    query_insert += "\n";
                }
                query_insert_faixa += "INSERT INTO FAIXA_001 (CODIGO, DESCRICAO, TAMANHO, ATIVO) VALUES (";
                query_insert_faixa += "\'" + (max_faixa + i) + "\'', \'" + 
                                lista[i].replaceAll(";", "/").substring(0, Math.min(lista[i].length(), 99)) +
                                    "\'', \'6\'', \'S\'');";
                query_insert_faixa += "\n";
            } else {
                break;
            }
        }
        try {
            StringSelection stringSelection = new StringSelection(query_insert + "\n------------------\n" + query_insert_faixa);   
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); 
            // Copiando a string para a área de transferência
            clipboard.setContents(stringSelection, null);
               }
               catch (Exception e ) {
                   System.out.print("");
               }
        System.out.print("Inserts copiados para a área de transferência! Cole no seu bloco de notas");
    }
}
