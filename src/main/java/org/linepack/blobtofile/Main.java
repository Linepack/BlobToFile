/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.blobtofile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.linepack.blobtofile.model.ConexaoManager;

/**
 *
 * @author Giovana
 */
public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        String query = args[0];
        String nomeArquvo = args[1];
        Blob arquivo = null;

        Connection conn;
        ConexaoManager manager = new ConexaoManager("oracle:thin",
                "host",
                1521,
                "SSID",
                "USER",
                "PWD");

        conn = manager.getConnection();
        Statement stmt;

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            arquivo = rs.getBlob(1);
        }
        mostreArquivoNaTela(arquivo, nomeArquvo);
    }

    private static void mostreArquivoNaTela(Blob blob, String nomeArquivo) throws FileNotFoundException, IOException, SQLException {
        
        String pathUser = System.getProperty("java.io.tmpdir") + "\\";
        String fileName = pathUser + nomeArquivo;
        InputStream is = blob.getBinaryStream();
        FileOutputStream fos = null;
        fos = new FileOutputStream(fileName);
        Integer iterator = 0;

        while ((iterator = is.read()) != -1) {
            fos.write(iterator);
        }

        fos.close();
        is.close();

        File arquivo = new File(fileName);
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        desktop.open(arquivo);

    }

}
