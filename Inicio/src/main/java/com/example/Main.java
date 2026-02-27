package com.example;

import com.arquitectura.fachada.Fachada;
import com.example.VistaConsola.VistaConsolaApp;
import com.example.VistaEscritorio.VistaEscritorioApp;
import com.example.config.JpaUtil;
import com.example.controller.ClienteController;
import com.example.controller.CuentaController;
import com.example.controller.FachadaController;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClienteController clienteController = new ClienteController(JpaUtil.getEntityManagerFactory());
        CuentaController cuentaController = new CuentaController(JpaUtil.getEntityManagerFactory());
        FachadaController fachadaController = new FachadaController(new Fachada());

        String modo = obtenerModoVista(args);

        try {
            switch (modo) {
                case "consola" -> new VistaConsolaApp(clienteController, cuentaController, fachadaController).iniciar();
                case "escritorio" -> VistaEscritorioApp.iniciarYEsperar(clienteController, cuentaController, fachadaController);
                default -> System.out.println("Modo no soportado: " + modo);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Ejecución interrumpida");
        } finally {
            JpaUtil.close();
        }
    }

    private static String obtenerModoVista(String[] args) {
        if (args.length > 0) {
            return args[0].toLowerCase(Locale.ROOT);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione vista de ejecución (consola/escritorio): ");
        return scanner.nextLine().trim().toLowerCase(Locale.ROOT);
    }
}
