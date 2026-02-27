package com.arquitectura.textoPlano;

import com.arquitectura.interfaces.Imprimible;



public class ComponenteTextoPlano implements Imprimible{

    private static ComponenteTextoPlano instancia;

    private ComponenteTextoPlano(){}

    public static ComponenteTextoPlano getInstancia(){
        if(instancia == null){
            instancia = new ComponenteTextoPlano();
        }
        return instancia;
    }

    public void imprimir(String contenido){

        System.out.println(contenido);

    }
}
