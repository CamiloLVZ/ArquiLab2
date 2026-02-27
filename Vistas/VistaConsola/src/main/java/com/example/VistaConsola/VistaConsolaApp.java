package com.example.VistaConsola;

import com.example.controller.ClienteController;
import com.example.controller.CuentaController;
import com.example.controller.FachadaController;
import com.arquitectura.dto.ClienteDTO;
import com.arquitectura.dto.CuentaAhorrosDTO;
import com.arquitectura.dto.CuentaCreditoDTO;

import java.util.List;
import java.util.Scanner;

public class VistaConsolaApp {

    private final ClienteController clienteController;
    private final CuentaController cuentaController;
    private final FachadaController fachadaController;
    private final Scanner scanner;

    public VistaConsolaApp(ClienteController clienteController,
                           CuentaController cuentaController,
                           FachadaController fachadaController) {
        this.clienteController = clienteController;
        this.cuentaController = cuentaController;
        this.fachadaController = fachadaController;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== Vista Consola iniciada ===");
        int sistema = leerEntero("Seleccione sistema (1=Bancario, 2=Clientes/Documentos): ");

        if (sistema == 1) {
            ejecutarSistemaBancario();
        } else if (sistema == 2) {
            ejecutarSistemaClientesDocumentos();
        } else {
            System.out.println("Opción no válida. Saliendo...");
        }
    }

    private void ejecutarSistemaBancario() {
        boolean continuar = true;
        while (continuar) {
            imprimirMenuBancario();
            int opcion = leerEntero("Seleccione una opción: ");
            try {
                switch (opcion) {
                    case 1 -> crearCliente();
                    case 2 -> crearCuentaAhorros();
                    case 3 -> crearCuentaCredito();
                    case 4 -> depositar();
                    case 5 -> transferir();
                    case 6 -> retirar();
                    case 7 -> avanceCredito();
                    case 8 -> abonarCredito();
                    case 9 -> listarClientes();
                    case 10 -> listarCuentasAhorros();
                    case 11 -> listarCuentasCredito();
                    case 0 -> {
                        continuar = false;
                        System.out.println("Saliendo de Vista Consola...");
                    }
                    default -> System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void ejecutarSistemaClientesDocumentos() {
        boolean continuar = true;
        while (continuar) {
            imprimirMenuClientesDocumentos();
            int opcion = leerEntero("Seleccione una opción: ");
            try {
                switch (opcion) {
                    case 1 -> procesarClienteYDocumento();
                    case 2 -> mostrarResumenFachada();
                    case 0 -> {
                        continuar = false;
                        System.out.println("Saliendo de Vista Consola...");
                    }
                    default -> System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void imprimirMenuBancario() {
        System.out.println("""
                --- Menú Bancario ---
                1. Crear cliente
                2. Crear cuenta de ahorros
                3. Crear cuenta de crédito
                4. Depositar en ahorros
                5. Transferir entre ahorros
                6. Retirar de ahorros
                7. Retirar avance de crédito
                8. Abonar deuda de crédito
                9. Listar clientes
                10. Listar cuentas de ahorros
                11. Listar cuentas de crédito
                0. Salir
                """);
    }

    private void imprimirMenuClientesDocumentos() {
        System.out.println("""
                --- Menú Clientes / Documentos (Fachada) ---
                1. Procesar cliente y generar documento
                2. Ver resumen de integración
                0. Salir
                """);
    }

    private void procesarClienteYDocumento() {
        double id = leerDouble("ID cliente (numérico): ");
        String nombre = leerTexto("Nombre: ");
        String apellidos = leerTexto("Apellidos: ");
        String email = leerTexto("Email destino: ");
        String mensaje = leerTexto("Mensaje: ");
        String tipoDocumento = leerTexto("Tipo documento (pdf/html/txt): ");

        fachadaController.procesarClienteYDocumento(id, nombre, apellidos, email, mensaje, tipoDocumento);
        System.out.println("Proceso ejecutado usando la interfaz de fachada.");
    }

    private void mostrarResumenFachada() {
        System.out.println("Resumen: " + fachadaController.obtenerResumen());
    }

    private void crearCliente() {
        String nombre = leerTexto("Nombre: ");
        String documento = leerTexto("Documento: ");
        ClienteDTO clienteDTO = clienteController.crearCliente(nombre, documento);
        System.out.println("Cliente creado: " + clienteDTO.getNombre() + " | Documento: " + clienteDTO.getDocumento());
    }

    private void crearCuentaAhorros() {
        Long clienteId = leerLong("ID del cliente: ");
        double saldo = leerDouble("Saldo inicial: ");
        CuentaAhorrosDTO cuenta = cuentaController.crearCuentaAhorros(clienteId, saldo);
        System.out.println("Cuenta ahorros creada: " + cuenta.getNumeroCuenta() + " | Titular: " + cuenta.getTitular() + " | Saldo: " + cuenta.getSaldo());
    }

    private void crearCuentaCredito() {
        Long clienteId = leerLong("ID del cliente: ");
        double cupo = leerDouble("Cupo total: ");
        CuentaCreditoDTO cuenta = cuentaController.crearCuentaCredito(clienteId, cupo);
        System.out.println("Cuenta crédito creada: " + cuenta.getNumeroCuenta() + " | Titular: " + cuenta.getTitular() + " | Cupo: " + cuenta.getCupoTotal());
    }

    private void depositar() {
        String numero = leerTexto("Número de cuenta ahorros: ");
        double valor = leerDouble("Valor a depositar: ");
        boolean ok = cuentaController.depositar(numero, valor);
        System.out.println(ok ? "Depósito exitoso" : "No se pudo depositar");
    }

    private void transferir() {
        String origen = leerTexto("Cuenta origen: ");
        String destino = leerTexto("Cuenta destino: ");
        double valor = leerDouble("Valor a transferir: ");
        boolean ok = cuentaController.transferir(origen, destino, valor);
        System.out.println(ok ? "Transferencia exitosa" : "No se pudo transferir");
    }

    private void retirar() {
        String numero = leerTexto("Número de cuenta ahorros: ");
        double valor = leerDouble("Valor a retirar: ");
        boolean ok = cuentaController.retirar(numero, valor);
        System.out.println(ok ? "Retiro exitoso" : "No se pudo retirar");
    }

    private void avanceCredito() {
        String numero = leerTexto("Cuenta crédito: ");
        double valor = leerDouble("Valor de avance: ");
        boolean ok = cuentaController.retirarAvanceCredito(numero, valor);
        System.out.println(ok ? "Avance exitoso" : "No se pudo retirar avance");
    }

    private void abonarCredito() {
        String numero = leerTexto("Cuenta crédito: ");
        double valor = leerDouble("Valor a abonar: ");
        boolean ok = cuentaController.abonarDeudaCredito(numero, valor);
        System.out.println(ok ? "Abono exitoso" : "No se pudo abonar");
    }

    private void listarClientes() {
        List<ClienteDTO> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes");
            return;
        }
        clientes.forEach(c -> System.out.println(c.getNombre() + " | Documento: " + c.getDocumento()));
    }

    private void listarCuentasAhorros() {
        List<CuentaAhorrosDTO> cuentas = cuentaController.listarCuentasAhorros();
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas de ahorros");
            return;
        }
        cuentas.forEach(c -> System.out.println(c.getNumeroCuenta() + " | Titular: " + c.getTitular() + " | Saldo: " + c.getSaldo()));
    }

    private void listarCuentasCredito() {
        List<CuentaCreditoDTO> cuentas = cuentaController.listarCuentasCredito();
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas de crédito");
            return;
        }
        cuentas.forEach(c -> System.out.println(c.getNumeroCuenta() + " | Titular: " + c.getTitular() + " | Deuda actual: " + c.getDeudaActual() + " | Cupo: " + c.getCupoTotal()));
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private Long leerLong(String mensaje) {
        while (true) {
            try {
                return Long.parseLong(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor numérico válido.");
            }
        }
    }
}