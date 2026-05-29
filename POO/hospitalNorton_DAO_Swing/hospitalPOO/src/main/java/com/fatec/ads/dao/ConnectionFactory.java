package com.fatec.ads.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionFactory {

    private static final String URL = "jdbc:sqlite:hospital_norton.db";

    public static Connection getConnection() throws Exception {
        Connection conn = DriverManager.getConnection(URL);
        createTables(conn);
        return conn;
    }

    private static void createTables(Connection conn) throws Exception {
        String sqlMedico = """
            CREATE TABLE IF NOT EXISTS medico (
                id          INTEGER PRIMARY KEY AUTOINCREMENT,
                nome        TEXT    NOT NULL,
                crm         TEXT    NOT NULL UNIQUE,
                telefone    TEXT,
                especialidade TEXT,
                senha       TEXT
            );
            """;

        String sqlPaciente = """
            CREATE TABLE IF NOT EXISTS paciente (
                id    INTEGER PRIMARY KEY AUTOINCREMENT,
                nome  TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE
            );
            """;

        String sqlConsulta = """
            CREATE TABLE IF NOT EXISTS consulta (
                id          INTEGER PRIMARY KEY AUTOINCREMENT,
                data        TEXT NOT NULL,
                hora        TEXT NOT NULL,
                motivo      TEXT,
                historico   TEXT,
                medico_id   INTEGER REFERENCES medico(id),
                paciente_id INTEGER REFERENCES paciente(id)
            );
            """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlMedico);
            stmt.execute(sqlPaciente);
            stmt.execute(sqlConsulta);
        }
    }
}
