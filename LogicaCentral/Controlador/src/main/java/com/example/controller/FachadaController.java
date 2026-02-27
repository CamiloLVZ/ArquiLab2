package com.example.controller;

import com.arquitectura.fachada.IFachada;

public class FachadaController {

    private final IFachada fachada;

    public FachadaController(IFachada fachada) {
        this.fachada = fachada;
    }

    public void procesarClienteYDocumento(double id,
                                          String nombre,
                                          String apellidos,
                                          String emailDestino,
                                          String mensaje,
                                          String tipoDocumento) {
        fachada.procesarClienteYDocumento(id, nombre, apellidos, emailDestino, mensaje, tipoDocumento);
    }

    public String obtenerResumen() {
        return fachada.obtenerResumen();
    }
}
