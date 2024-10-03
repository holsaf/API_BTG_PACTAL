package com.btg.operaciones.services.cliente;

import com.btg.operaciones.entities.Cliente;
import com.btg.operaciones.repositories.ClienteRepository;
import com.btg.operaciones.services.cliente.ClienteService;
import org.springframework.stereotype.Service;


@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository){

        this.clienteRepository = clienteRepository;
    }

    public Cliente consultarCliente(String id) {
        return this.clienteRepository.findById(id).orElse(null);
    }

    public Cliente crearCliente(Cliente cliente) {
        return this.clienteRepository.save(cliente);
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
        clienteRepository.save(clienteActual);
    }
}
