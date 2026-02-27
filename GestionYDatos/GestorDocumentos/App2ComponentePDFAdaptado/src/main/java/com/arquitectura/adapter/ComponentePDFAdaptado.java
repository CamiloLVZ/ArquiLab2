package com.arquitectura.adapter;

import com.arquitectura.interfaces.Imprimible;
import com.arquitectura.pdf.ComponentePDF;

public class ComponentePDFAdaptado implements Imprimible{

    private final ComponentePDF pdf;

    private static ComponentePDFAdaptado instancia;


    private ComponentePDFAdaptado(){
        this.pdf = new ComponentePDF();
    }

    public static ComponentePDFAdaptado getInstancia(){
        if(instancia == null){
            instancia = new ComponentePDFAdaptado();
        }
        return instancia;
    }

    public void imprimir(String contenido){

        pdf.imprimirPDF(contenido);
    }
}