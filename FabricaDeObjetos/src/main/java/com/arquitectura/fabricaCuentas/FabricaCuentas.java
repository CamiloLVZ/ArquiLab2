package com.arquitectura.fabricaCuentas;


import com.example.entity.Cliente;
import com.example.entity.Cuenta;
import com.example.entity.CuentaDeAhorros;
import com.example.entity.CuentaDeCredito;

public class FabricaCuentas {

    public static final String AHORROS = "AHORROS";
    public static final String CREDITO = "CREDITO";

    public Cuenta crearCuenta(String tipo, Cliente titular, double monto) {
        switch (tipo.toUpperCase()) {
            case AHORROS:
                return new CuentaDeAhorros("AC-" + System.currentTimeMillis(), titular, monto);
            case CREDITO:
                return new CuentaDeCredito("CC-" + System.currentTimeMillis(), titular, monto);
            default:
                throw new IllegalArgumentException("Tipo de cuenta desconocido: " + tipo);
        }
    }

}
