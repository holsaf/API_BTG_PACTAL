package com.btg.operaciones.services.cliente;

import com.btg.operaciones.entities.Cliente;

public interface ClienteService {

    Cliente consultarCliente(String id);

    Cliente crearCliente(Cliente cliente);

    double consultarSaldoCliente(String id);

    void actualizarCliente(Cliente cliente);
}
