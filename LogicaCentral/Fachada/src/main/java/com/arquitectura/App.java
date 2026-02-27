package com.arquitectura;

import com.arquitectura.fachada.Fachada;

public class App
{
    public static void main( String[] args )
    {

        // 1. Crear la fachada
        Fachada fachada = new Fachada();

        // 2. Usar la fachada (el controller NO conoce subsistemas)
        fachada.procesarClienteYDocumento(
                1,
                "Juan",
                "Burgos",
                "juan@email.com",
                "Mensaje desde el Controller",
                "HTML"   // HTML | PDF | TEXTO
        );

        // 3. Mostrar resumen
        System.out.println(fachada.obtenerResumen());
    }
}
