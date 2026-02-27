package com.arquitectura.fachada;

import com.arquitectura.sistemaClientes.patron.facade.IFacade;
import com.arquitectura.sistemaClientes.patron.facade.ImplementacionFachada;
import com.arquitectura.GestorImpresion.GestorImpresion;

public class Fachada implements IFachada {

    private IFacade sistemaClientes;
    private GestorImpresion gestorImpresion;

    public Fachada() {
        this.sistemaClientes = new ImplementacionFachada();
        this.gestorImpresion = new GestorImpresion();
    }

    @Override
    public void procesarClienteYDocumento(double id,
                                          String nombre,
                                          String apellidos,
                                          String emailDestino,
                                          String mensaje,
                                          String tipoDocumento) {

        // 1. Delegar al sistema de clientes
        sistemaClientes.enviarInformacionSubsistemas(
                id, nombre, apellidos, emailDestino, mensaje
        );

        // 2. Construir documento
        String contenidoDocumento =
                "Cliente: " + nombre + " " + apellidos +
                        "\nEmail: " + emailDestino +
                        "\nMensaje: " + mensaje;

        // 3. Delegar al gestor de impresión
        gestorImpresion.imprimir(tipoDocumento, contenidoDocumento);
    }

    @Override
    public String obtenerResumen() {
        return sistemaClientes.informacionEnviadaSubsistema();
    }
}