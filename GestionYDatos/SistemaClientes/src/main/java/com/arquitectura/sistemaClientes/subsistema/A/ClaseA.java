package com.arquitectura.sistemaClientes.subsistema.A;

public class ClaseA {

    private double id;
    private String nombre;
    private String apellido;

    public void cargarInformacionTerceros(){
        System.out.println("Informacion enviada al sistema contable.");

    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

