package com.example.controller;

import com.example.entity.CuentaDeAhorros;
import com.example.entity.CuentaDeCredito;
import com.example.service.CuentaService;
import com.arquitectura.dto.CuentaAhorrosDTO;
import com.arquitectura.dto.CuentaCreditoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CuentaController {

    private final EntityManagerFactory entityManagerFactory;

    public CuentaController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public CuentaAhorrosDTO crearCuentaAhorros(Long clienteId, double saldoInicial) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            CuentaDeAhorros cuenta = new CuentaService(em).crearCuentaDeAhorros(clienteId, saldoInicial);
            em.getTransaction().commit();
            return new CuentaAhorrosDTO(cuenta.getNumeroCuenta(), cuenta.getSaldo(), cuenta.getTitular().getNombre());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public CuentaCreditoDTO crearCuentaCredito(Long clienteId, double cupoTotal) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            CuentaDeCredito cuenta = new CuentaService(em).crearCuentaDeCredito(clienteId, cupoTotal);
            em.getTransaction().commit();
            return new CuentaCreditoDTO(cuenta.getNumeroCuenta(), cuenta.getDeudaActual(), cuenta.getCupoTotal(), cuenta.getTitular().getNombre());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean depositar(String numeroCuenta, double valor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            boolean resultado = new CuentaService(em).depositar(numeroCuenta, valor);
            em.getTransaction().commit();
            return resultado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean retirar(String numeroCuenta, double valor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            boolean resultado = new CuentaService(em).retirar(numeroCuenta, valor);
            em.getTransaction().commit();
            return resultado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean transferir(String origen, String destino, double valor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            boolean resultado = new CuentaService(em).transferir(origen, destino, valor);
            em.getTransaction().commit();
            return resultado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean retirarAvanceCredito(String numeroCuenta, double valor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            boolean resultado = new CuentaService(em).retirarAvanceCredito(numeroCuenta, valor);
            em.getTransaction().commit();
            return resultado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean abonarDeudaCredito(String numeroCuenta, double valor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            boolean resultado = new CuentaService(em).abonarDeudaCredito(numeroCuenta, valor);
            em.getTransaction().commit();
            return resultado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<CuentaAhorrosDTO> listarCuentasAhorros() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            List<CuentaDeAhorros> cuentas = new CuentaService(em).listarCuentasAhorros();
            return cuentas.stream()
                    .map(c -> new CuentaAhorrosDTO(c.getNumeroCuenta(), c.getSaldo(), c.getTitular().getNombre()))
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    public List<CuentaCreditoDTO> listarCuentasCredito() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            List<CuentaDeCredito> cuentas = new CuentaService(em).listarCuentasCredito();
            return cuentas.stream()
                    .map(c -> new CuentaCreditoDTO(c.getNumeroCuenta(), c.getDeudaActual(), c.getCupoTotal(), c.getTitular().getNombre()))
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }
}