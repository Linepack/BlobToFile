/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.blobtofile.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Giovana
 */
public class ConexaoManager {           

    private final String userName;
    private final String password;
    private final String dbms;
    private final String serverName;
    private final Integer portNumber;
    private final String dbName;

    public ConexaoManager(String dbms,
            String serverName,
            Integer portNumber,
            String dbName,
            String userName,
            String password) {
        this.dbms = dbms;
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {

        String stringConexao = "jdbc:" + this.dbms + ":@" + this.serverName + ":" + this.portNumber + ":" + this.dbName;
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        conn = DriverManager.getConnection(stringConexao, connectionProps);
        conn.setAutoCommit(false);                    
        return conn;

    }
}
