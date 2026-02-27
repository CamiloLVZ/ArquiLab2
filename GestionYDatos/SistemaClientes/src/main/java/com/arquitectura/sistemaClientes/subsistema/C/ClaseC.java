package com.arquitectura.sistemaClientes.subsistema.C;

public class ClaseC {

    private String texto;

    public ClaseC(String texto) {
        this.texto = texto;
    }

    public void procesarInformacion(){
        System.out.println("Información procesada para configuración");
        System.out.println("\tCorreo electronicoy demás configuraciones creadas: " + texto.toUpperCase());

    }

    public String getTexto() {
        return texto.toUpperCase();
    }

}
