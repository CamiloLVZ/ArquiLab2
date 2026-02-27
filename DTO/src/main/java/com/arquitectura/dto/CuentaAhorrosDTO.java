package com.arquitectura.dto;

public class CuentaAhorrosDTO {

    private String numeroCuenta;
    private double saldo;
    private String titular;

    public CuentaAhorrosDTO(String numeroCuenta, double saldo, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.titular = titular;
    }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }

}
