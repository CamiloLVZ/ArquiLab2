package com.arquitectura.sistemaClientes.h2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionH2 {

    private static final String URL = "jdbc:h2:././GestionYDatos/SistemaClientes/basedatos/clientes";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}