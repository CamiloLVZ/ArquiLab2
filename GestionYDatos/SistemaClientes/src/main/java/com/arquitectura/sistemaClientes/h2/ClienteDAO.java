package com.arquitectura.sistemaClientes.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            System.out.println("Cliente guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error guardando cliente: " + e.getMessage());
        }
    }

    public String consultar(double id) {
        String sql = "SELECT * FROM CLIENTES WHERE ID = ?";
        String resultado = "";

        try (Connection con = ConexionH2.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                resultado = "\n--- Cliente desde BD (H2) ---" +
                        "\nID: " + rs.getDouble("ID") +
                        "\nNombre: " + rs.getString("NOMBRE") +
                        "\nApellido: " + rs.getString("APELLIDO") +
                        "\nEmail: " + rs.getString("EMAIL") +
                        "\nMensaje: " + rs.getString("MENSAJE");
            } else {
                resultado = "\nCliente con ID " + id + " no encontrado en BD.";
            }

        } catch (Exception e) {
            resultado = "\nError consultando cliente: " + e.getMessage();
        }

        return resultado;
    }

    public String consultarTodos() {
        String sql = "SELECT * FROM CLIENTES ORDER BY ID";
        StringBuilder resultado = new StringBuilder("\n--- Clientes en BD (H2) ---");

        try (Connection con = ConexionH2.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            int contador = 0;

            while (rs.next()) {
                contador++;
                resultado.append("\n\n[Cliente ").append(contador).append("]")
                        .append("\n  ID: ").append(rs.getDouble("ID"))
                        .append("\n  Nombre: ").append(rs.getString("NOMBRE"))
                        .append("\n  Apellido: ").append(rs.getString("APELLIDO"))
                        .append("\n  Email: ").append(rs.getString("EMAIL"))
                        .append("\n  Mensaje: ").append(rs.getString("MENSAJE"));
            }

            if (contador == 0) {
                resultado.append("\nNo hay clientes registrados en la BD.");
            } else {
                resultado.append("\n\nTotal de clientes: ").append(contador);
            }

            System.out.println("[DEBUG] Se encontraron " + contador + " clientes");

        } catch (Exception e) {
            String errorMsg = "\nError consultando clientes: " + e.getMessage();
            System.out.println("[DEBUG] " + errorMsg);
            resultado = new StringBuilder(errorMsg);
        }

        return resultado.toString();
    }
}