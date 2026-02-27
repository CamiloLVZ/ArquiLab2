package com.arquitectura.sistemaClientes.patron.facade;

import com.arquitectura.sistemaClientes.subsistema.A.ClaseA;
import com.arquitectura.sistemaClientes.subsistema.B.ClaseB;
import com.arquitectura.sistemaClientes.subsistema.C.ClaseC;
import com.arquitectura.sistemaClientes.h2.ClienteDAO;

public class ImplementacionFachada implements IFacade {

    private ClaseA claseA;
    private ClaseB claseB;
    private ClaseC claseC;

    private ClienteDAO clienteDAO;

    public ImplementacionFachada() {
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public void enviarInformacionSubsistemas(double id, String nombre, String apellidos,
                                             String email_destino, String texto_mensaje) {

        claseA = new ClaseA();
        claseA.setId(id);
        claseA.setNombre(nombre);
        claseA.setApellido(apellidos);
        claseA.cargarInformacionTerceros();

        claseB = new ClaseB(email_destino, texto_mensaje);
        claseB.enviarMensaje();

        claseC = new ClaseC(id + "; " + nombre + "; " + apellidos);
        claseC.procesarInformacion();

        clienteDAO.guardar(id, nombre, apellidos, email_destino, texto_mensaje);
    }

    @Override
    public String informacionEnviadaSubsistema() {
        String texto = "\n--- Informacion enviada ---";

        if (claseA != null && claseB != null && claseC != null) {
            texto += "\nClaseA: " + claseA.getId() + " " +
                    claseA.getNombre() + " " +
                    claseA.getApellido();

            texto += "\nClaseB: " + claseB.mensajeEnviado();
            texto += "\nClaseC: " + claseC.getTexto();
        }

        return texto;
    }
}