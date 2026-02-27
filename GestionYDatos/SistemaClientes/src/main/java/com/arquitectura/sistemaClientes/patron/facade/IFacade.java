package com.arquitectura.sistemaClientes.patron.facade;

public interface IFacade {

    public void enviarInformacionSubsistemas(double id,
                                             String nombre,
                                             String apellidos,
                                             String email_destino,
                                             String texto_mensaje);

    public String informacionEnviadaSubsistema();
}
