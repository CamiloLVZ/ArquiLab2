package com.arquitectura.sistemaClientes.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ClienteDAO {

    public ClienteDAO() {
        crearTabla();
    }

    private void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS CLIENTES (" +
                "ID DOUBLE PRIMARY KEY, " +
                "NOMBRE VARCHAR(100), " +
                "APELLIDO VARCHAR(100), " +
                "EMAIL VARCHAR(100), " +
                "MENSAJE VARCHAR(255))";

        try (Connection con = ConexionH2.obtenerConexion();
             Statement st = con.createStatement()) {

            st.execute(sql);

        } catch (Exception e) {
            System.out.println("Error creando tabla: " + e.getMessage());
        }
    }

    public void guardar(double id, String nombre, String apellido,
                        String email, String mensaje) {

        String sql = "INSERT INTO CLIENTES (ID, NOMBRE, APELLIDO, EMAIL, MENSAJE) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionH2.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, id);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, email);
            ps.setString(5, mensaje);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error guardando cliente: " + e.getMessage());
        }
    }
}