package com.exampl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String excelFilePath = "C:\\Users\\Administrator\\Desktop\\Importação\\Exportacao de Produtos.xlsx";
        String jdbcURL = "jdbc:postgresql://10.0.1.253:5432/16143P_TESTE";
        String username = "importacao";
        String password = "8f3d32Lp1u92wtf1yw(n";

        try {
            // Lendo o arquivo Excel
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // Conectando ao banco de dados
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            // Iterando sobre as linhas do Excel e inserindo no banco de dados
            for (Row row : sheet) {
                //nessas atribuições, o getCell deve ser sempre 1 número a menos que a posição da coluna.
                //então, a coluna C é getCell(2), coluna E é getCell(4)
                Cell url_imagemCelula = row.getCell(2);
                Cell codigoCelula = row.getCell(0);
                Cell nomeCelula = row.getCell(1);
                Cell desc_curtaCelula = row.getCell(3);
                Cell desc_longa_htmlCelula = row.getCell(4);
                 
                String url_imagem = url_imagemCelula.getStringCellValue();
                url_imagem = url_imagem.length() > 200 ? url_imagem.substring(1,200) : url_imagem;

                String nome = nomeCelula.getStringCellValue();
                nome = nome.length() > 300 ? nome.substring(1,300) : nome;

                String desc_curta = desc_curtaCelula.getStringCellValue();
                desc_curta = desc_curta.length() > 1024 ? desc_curta.substring(1,1024) : desc_curta;

                String codigo = codigoCelula == null ? "" : codigoCelula.getStringCellValue();
                String descricaoHtml = desc_longa_htmlCelula == null ? "" : desc_longa_htmlCelula.getStringCellValue();
                byte[] desc_longa_html = descricaoHtml.getBytes(Charset.forName("windows-1252"));
                
                String sql = "INSERT INTO ecommerceproduto_bkp " +
                "(codigo, desc_longa_html, url_imagem, desc_curta, nome)" +
                " VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, codigo);
                statement.setBytes(2, desc_longa_html);         
                statement.setString(3, url_imagem);
                statement.setString(4, desc_curta);
                statement.setString(5, nome);
                statement.executeUpdate();
            }

            workbook.close();
            connection.close();

            System.out.println("Dados ajustados com sucesso!");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
