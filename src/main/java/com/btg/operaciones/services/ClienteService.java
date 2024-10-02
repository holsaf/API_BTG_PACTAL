package com.btg.operaciones.services;

import com.btg.operaciones.entities.Cliente;
import com.btg.operaciones.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){

        this.clienteRepository = clienteRepository;
    }

    public Cliente consultarClienteRepository(String id) {
        return this.clienteRepository.findById(id).orElse(null);
    }

    public double consultarSaldoCliente(String id) {
        var cliente = this.clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            return 0.0;
        }
        return cliente.getSaldo();
    }

    public void actualizarCliente(Cliente cliente) {
        var clienteActual = clienteRepository.findById(cliente.getId()).orElse(null);
        if (clienteActual == null) {
            return;
        }
        clienteActual.setNombre(cliente.getNombre());
        clienteActual.setEmail(cliente.getEmail());
        clienteActual.setTelefono(cliente.getTelefono());
        clienteActual.setSaldo(cliente.getSaldo());
        clienteActual.setFondos(cliente.getFondos());
        clienteRepository.save(clienteActual);
    }
}
