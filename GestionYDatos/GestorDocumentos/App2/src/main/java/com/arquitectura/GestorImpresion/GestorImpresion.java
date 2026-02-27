package com.arquitectura.GestorImpresion;

import com.arquitectura.interfaces.Imprimible;
import com.arquitectura.fabricaImpresiones.FabricaImpresiones;

public class GestorImpresion {

    public void imprimir(String tipo, String contenido){
        Imprimible impresor = FabricaImpresiones.crear(tipo);
        impresor.imprimir(contenido);
    }

}