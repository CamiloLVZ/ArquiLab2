package com.arquitectura.dto;

public class CuentaCreditoDTO {

    private String numeroCuenta;
    private double deudaActual;
    private double cupoTotal;
    private String titular;

    public CuentaCreditoDTO(String numeroCuenta, double deudaActual, double cupoTotal, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.deudaActual = deudaActual;
        this.cupoTotal = cupoTotal;
        this.titular = titular;
    }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public double getDeudaActual() { return deudaActual; }
    public void setDeudaActual(double deudaActual) { this.deudaActual = deudaActual; }

    public double getCupoTotal() { return cupoTotal; }
    public void setCupoTotal(double cupoTotal) { this.cupoTotal = cupoTotal; }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }


}
