package com.projetofinal.ninjatask.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String USER ="system";
    private static final String PASS = "oracle";

    private static final String SERVER = "localhost";
    private static final String PORT = "1521";
    private static final String DATABASE = "xe";


    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABASE;
        Connection connection = DriverManager.getConnection(url,USER,PASS);
        connection.createStatement().execute("alter session set current_schema=JORNADA");

        return connection;
    }
}
