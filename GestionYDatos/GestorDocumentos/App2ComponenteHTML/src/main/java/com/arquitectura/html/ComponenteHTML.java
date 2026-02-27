package com.arquitectura.html;

import com.arquitectura.interfaces.Imprimible;
import com.arquitectura.htmlBuilder.HTMLBuilder;

public class ComponenteHTML implements Imprimible{

    private static ComponenteHTML instancia;

    private ComponenteHTML(){}

    public static ComponenteHTML getInstancia(){

        if(instancia == null){
            instancia = new ComponenteHTML();
        }
        return instancia;
    }

    public void imprimir(String contenido){

        HTMLBuilder builder = new HTMLBuilder();

        String html = builder
                .head("Documento HTML")
                .header("Soy el header")
                .h1(contenido)
                .build();

        System.out.println(html);

    }
}