package com.example.controller;

import java.util.stream.Collectors;
import com.example.entity.Cliente;
import com.example.service.ClienteService;
import com.arquitectura.dto.ClienteDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ClienteController {

    private final EntityManagerFactory entityManagerFactory;

    public ClienteController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public ClienteDTO crearCliente(String nombre, String documento) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = new ClienteService(em).crearCliente(nombre, documento);
            em.getTransaction().commit();

            return new ClienteDTO(cliente.getNombre(), cliente.getDocumento());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<ClienteDTO> listarClientes() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            List<Cliente> clientes = new ClienteService(em).listarClientes();

            return clientes.stream()
                    .map(c -> new ClienteDTO(c.getNombre(), c.getDocumento()))
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }
}
