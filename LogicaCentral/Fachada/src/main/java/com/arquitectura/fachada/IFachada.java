package com.arquitectura.fachada;

public interface IFachada {

    void procesarClienteYDocumento(double id,
                                   String nombre,
                                   String apellidos,
                                   String emailDestino,
                                   String mensaje,
                                   String tipoDocumento);

    String obtenerResumen();

}
