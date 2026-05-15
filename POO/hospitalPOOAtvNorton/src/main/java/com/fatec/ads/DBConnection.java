package com.fatec.ads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:h2:mem:hospital;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTables() throws SQLException {
        try (Connection conn = getConnection()) {
            conn.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS recepcionista (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(255), " +
                "telefone VARCHAR(255), " +
                "senha VARCHAR(255), " +
                "cpf VARCHAR(255))"
            );
            conn.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS medico (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(255), " +
                "telefone VARCHAR(255), " +
                "senha VARCHAR(255), " +
                "crm VARCHAR(255), " +
                "especialidade VARCHAR(255))"
            );
        }
    }
}