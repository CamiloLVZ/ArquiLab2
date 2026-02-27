package com.arquitectura.fabricaImpresiones;

import com.arquitectura.interfaces.Imprimible;
import com.arquitectura.html.ComponenteHTML;
import com.arquitectura.adapter.ComponentePDFAdaptado;
import com.arquitectura.textoPlano.ComponenteTextoPlano;


public class FabricaImpresiones {

    public static Imprimible crear(String tipo){

        switch (tipo.toUpperCase()){
            case "HTML":
                return ComponenteHTML.getInstancia();

            case "PDF":
                return ComponentePDFAdaptado.getInstancia();

            case "TEXTO":
                return ComponenteTextoPlano.getInstancia();

            default:
                throw new IllegalArgumentException("Tipo de Impresor no valido: " + tipo);
        }
    }

}
