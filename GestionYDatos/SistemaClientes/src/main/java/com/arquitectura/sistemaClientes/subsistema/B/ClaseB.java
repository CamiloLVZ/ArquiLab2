package com.arquitectura.sistemaClientes.subsistema.B;

public class ClaseB {

    private String destino;
    private String remitente;
    private String mensaje;

    public ClaseB(String destino,String mensaje) {
        this.destino = destino;
        this.remitente = "***miempresa***";
        this.mensaje = mensaje;
    }

    public void enviarMensaje() {
        System.out.println("Informacion enviada el sistema de mensajes.");
        System.out.println("\tEnviando mensaje");
        System.out.println("\tDestino: " + destino);
        System.out.println("\tRemitente: " + remitente);
        System.out.println("\tMensaje: " + mensaje);
        System.out.println("\tMensaje enviado. ");
    }

    public String  mensajeEnviado(){

        return "\nDestino: " + destino + "\nRemitente: " + remitente + "\nMensaje: " + mensaje;
    }
}
