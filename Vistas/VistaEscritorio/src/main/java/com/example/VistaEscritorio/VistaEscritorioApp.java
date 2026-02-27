package com.example.VistaEscritorio;

import com.example.controller.ClienteController;
import com.example.controller.CuentaController;
import com.example.controller.FachadaController;
import com.arquitectura.dto.ClienteDTO;
import com.arquitectura.dto.CuentaAhorrosDTO;
import com.arquitectura.dto.CuentaCreditoDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class VistaEscritorioApp extends JFrame {

    private final ClienteController clienteController;
    private final CuentaController cuentaController;
    private final FachadaController fachadaController;
    private final JTextArea salida;

    public VistaEscritorioApp(ClienteController clienteController,
                              CuentaController cuentaController,
                              FachadaController fachadaController,
                              boolean modoBancario) {
        super("Banco - Vista Escritorio");
        this.clienteController = clienteController;
        this.cuentaController = cuentaController;
        this.fachadaController = fachadaController;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        if (modoBancario) {
            tabs.add("Cliente", panelCrearCliente());
            tabs.add("Ahorros", panelAhorros());
            tabs.add("Crédito", panelCredito());
            tabs.add("Listados", panelListados());
        } else {
            tabs.add("Fachada", panelFachada());
        }

        salida = new JTextArea();
        salida.setEditable(false);

        add(tabs, BorderLayout.CENTER);
        add(new JScrollPane(salida), BorderLayout.SOUTH);
    }


    private JPanel panelCrearCliente() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField nombreField = new JTextField();
        JTextField documentoField = new JTextField();
        JButton crearBtn = new JButton("Crear Cliente");

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Documento:"));
        panel.add(documentoField);
        panel.add(new JLabel());
        panel.add(crearBtn);

        crearBtn.addActionListener(e -> {
            try {
                ClienteDTO cliente = clienteController.crearCliente(
                        nombreField.getText(),
                        documentoField.getText()
                );
                salida.append("Cliente creado: " + cliente.getNombre() + " - " + cliente.getDocumento() + "\n");
            } catch (Exception ex) {
                salida.append("Error al crear cliente: " + ex.getMessage() + "\n");
            }
        });

        return panel;
    }

    private JPanel panelAhorros() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField clienteIdField = new JTextField();
        JTextField saldoField = new JTextField();
        JButton crearBtn = new JButton("Crear Cuenta de Ahorros");

        panel.add(new JLabel("ID Cliente:"));
        panel.add(clienteIdField);
        panel.add(new JLabel("Saldo inicial:"));
        panel.add(saldoField);
        panel.add(new JLabel());
        panel.add(crearBtn);

        crearBtn.addActionListener(e -> {
            try {
                long clienteId = Long.parseLong(clienteIdField.getText());
                double saldo = Double.parseDouble(saldoField.getText());
                CuentaAhorrosDTO cuenta = cuentaController.crearCuentaAhorros(clienteId, saldo);
                salida.append("Cuenta de Ahorros creada: " + cuenta.getNumeroCuenta() +
                        " - Saldo: " + cuenta.getSaldo() + "\n");
            } catch (Exception ex) {
                salida.append("Error al crear cuenta de ahorros: " + ex.getMessage() + "\n");
            }
        });

        return panel;
    }

    private JPanel panelCredito() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField clienteIdField = new JTextField();
        JTextField cupoField = new JTextField();
        JButton crearBtn = new JButton("Crear Cuenta de Crédito");

        panel.add(new JLabel("ID Cliente:"));
        panel.add(clienteIdField);
        panel.add(new JLabel("Cupo total:"));
        panel.add(cupoField);
        panel.add(new JLabel());
        panel.add(crearBtn);

        crearBtn.addActionListener(e -> {
            try {
                long clienteId = Long.parseLong(clienteIdField.getText());
                double cupo = Double.parseDouble(cupoField.getText());
                CuentaCreditoDTO credito = cuentaController.crearCuentaCredito(clienteId, cupo);
                salida.append("Cuenta de Crédito creada: " + credito.getNumeroCuenta() +
                        " - Cupo: " + credito.getCupoTotal() + "\n");
            } catch (Exception ex) {
                salida.append("Error al crear cuenta de crédito: " + ex.getMessage() + "\n");
            }
        });

        return panel;
    }

    private JPanel panelListados() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton listarClientesBtn = new JButton("Listar Clientes");
        JButton listarAhorrosBtn = new JButton("Listar Cuentas Ahorros");
        JButton listarCreditoBtn = new JButton("Listar Cuentas Crédito");

        panel.add(listarClientesBtn);
        panel.add(listarAhorrosBtn);
        panel.add(listarCreditoBtn);

        listarClientesBtn.addActionListener(e -> {
            try {
                List<ClienteDTO> clientes = clienteController.listarClientes();
                salida.append("----- Clientes -----\n");
                for (ClienteDTO c : clientes) {
                    salida.append(c.getNombre() + " - " + c.getDocumento() + "\n");
                }
            } catch (Exception ex) {
                salida.append("Error al listar clientes: " + ex.getMessage() + "\n");
            }
        });

        listarAhorrosBtn.addActionListener(e -> {
            try {
                List<CuentaAhorrosDTO> cuentas = cuentaController.listarCuentasAhorros();
                salida.append("----- Cuentas de Ahorros -----\n");
                for (CuentaAhorrosDTO c : cuentas) {
                    salida.append(c.getNumeroCuenta() + " - Saldo: " + c.getSaldo() +
                            " - Titular: " + c.getTitular() + "\n");
                }
            } catch (Exception ex) {
                salida.append("Error al listar cuentas de ahorros: " + ex.getMessage() + "\n");
            }
        });

        listarCreditoBtn.addActionListener(e -> {
            try {
                List<CuentaCreditoDTO> cuentas = cuentaController.listarCuentasCredito();
                salida.append("----- Cuentas de Crédito -----\n");
                for (CuentaCreditoDTO c : cuentas) {
                    salida.append(c.getNumeroCuenta() + " - Deuda: " + c.getDeudaActual() +
                            " - Cupo: " + c.getCupoTotal() +
                            " - Titular: " + c.getTitular() + "\n");
                }
            } catch (Exception ex) {
                salida.append("Error al listar cuentas de crédito: " + ex.getMessage() + "\n");
            }
        });

        return panel;
    }

    private JPanel panelFachada() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        JTextField idField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField apellidoField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField mensajeField = new JTextField();
        JTextField tipoDocField = new JTextField();
        JButton procesarBtn = new JButton("Procesar Cliente y Documento");

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Apellidos:"));
        panel.add(apellidoField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Mensaje:"));
        panel.add(mensajeField);
        panel.add(new JLabel("Tipo Documento (HTML/PDF/TEXTO):"));
        panel.add(tipoDocField);
        panel.add(new JLabel());
        panel.add(procesarBtn);

        procesarBtn.addActionListener(e -> {
            try {
                double id = Double.parseDouble(idField.getText());
                fachadaController.procesarClienteYDocumento(
                        id,
                        nombreField.getText(),
                        apellidoField.getText(),
                        emailField.getText(),
                        mensajeField.getText(),
                        tipoDocField.getText()
                );
                String resumen = fachadaController.obtenerResumen();
                salida.append("Fachada procesada. Resumen:\n" + resumen + "\n");
            } catch (Exception ex) {
                salida.append("Error en fachada: " + ex.getMessage() + "\n");
            }
        });

        return panel;
    }


    public static void iniciarYEsperar(ClienteController clienteController,
                                       CuentaController cuentaController,
                                       FachadaController fachadaController)
            throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            boolean modoBancario = seleccionarModoSistema();
            VistaEscritorioApp app = new VistaEscritorioApp(
                    clienteController, cuentaController, fachadaController, modoBancario
            );
            app.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    latch.countDown();
                }
            });
            app.setVisible(true);
        });
        latch.await();
    }

    private static boolean seleccionarModoSistema() {
        int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Desea ejecutar en modo bancario? (Sí = Bancario, No = Fachada)",
                "Seleccionar Modo",
                JOptionPane.YES_NO_OPTION
        );
        return opcion == JOptionPane.YES_OPTION;
    }
}