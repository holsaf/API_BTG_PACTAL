package com.btg.operaciones.services;

import com.btg.operaciones.entities.FondoCliente;
import com.btg.operaciones.entities.Transaccion;
import com.btg.operaciones.repositories.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final FondoService fondoService;

    private final ClienteService clienteService;

    public TransaccionService(TransaccionRepository transaccionRepository, FondoService fondoService, ClienteService clienteService) {
        this.transaccionRepository = transaccionRepository;
        this.fondoService = fondoService;
        this.clienteService = clienteService;
    }

    public List<Transaccion> consultarTransaccionesByCliente(String clienteId) {
        return transaccionRepository.findByClienteId(clienteId);
    }


    public Transaccion guardarTransaccion(Transaccion transaccion) {
        validacionesCliente(transaccion);
        validacionFondo(transaccion);
        actualizarSaldoFondo(transaccion);
        actualizarFondosCliente(transaccion);
        actualizarSaldoCliente(transaccion);
        return transaccionRepository.save(transaccion);
    }

    private void validacionFondo(Transaccion transaccion) {
        var fondo = fondoService.consultarFondo(transaccion.getFondoId());
        if (fondo == null) {
            throw new RuntimeException("Fondo no encontrado");
        }
        if (transaccion.getTipo().equals("APERTURA") && fondo.getMontoMinimoVinculacion() > transaccion.getMonto()) {
            throw new RuntimeException("El monto mínimo de vinculación es de " + fondo.getMontoMinimoVinculacion());
        }
    }

    private void validacionesCliente(Transaccion transaccion) {
        var cliente = clienteService.consultarClienteRepository(transaccion.getClienteId());
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }
        if (transaccion.getTipo().equals("CANCELAR") && cliente.getSaldo() < transaccion.getMonto()) {
            throw new RuntimeException("No tiene saldo disponible para vincularse al fondo <Nombre del fondo>");
        }
    }

    private void actualizarSaldoFondo(Transaccion transaccion) {
        if (transaccion.getTipo().equals("APERTURA")) {
            var fondo = fondoService.consultarFondo(transaccion.getFondoId());
            fondo.setSaldoTotal(fondo.getSaldoTotal() + transaccion.getMonto());
            fondoService.actualizarFondo(fondo);
        } else if (transaccion.getTipo().equals("CANCELAR")) {
            var fondo = fondoService.consultarFondo(transaccion.getFondoId());
            fondo.setSaldoTotal(fondo.getSaldoTotal() - transaccion.getMonto());
            fondoService.actualizarFondo(fondo);
        }
    }

    private void actualizarSaldoCliente(Transaccion transaccion) {
        if (transaccion.getTipo().equals("APERTURA")) {
            var cliente = clienteService.consultarClienteRepository(transaccion.getClienteId());
            cliente.setSaldo(cliente.getSaldo() - transaccion.getMonto());
            clienteService.actualizarCliente(cliente);
        } else if (transaccion.getTipo().equals("CANCELAR")) {
            var cliente = clienteService.consultarClienteRepository(transaccion.getClienteId());
            cliente.setSaldo(cliente.getSaldo() + transaccion.getMonto());
            clienteService.actualizarCliente(cliente);
        }
    }

    private void actualizarFondosCliente(Transaccion transaccion) {
        if (transaccion.getTipo().equals("APERTURA")) {
            var cliente = clienteService.consultarClienteRepository(transaccion.getClienteId());
            var nuevoFondoCliente = new FondoCliente();
            nuevoFondoCliente.setFondoId(transaccion.getFondoId());
            nuevoFondoCliente.setMontoInvertido(transaccion.getMonto());
            cliente.getFondos().add(nuevoFondoCliente);
            clienteService.actualizarCliente(cliente);
        } else if (transaccion.getTipo().equals("CANCELAR")) {
            var cliente = clienteService.consultarClienteRepository(transaccion.getClienteId());
            var fondoCliente = cliente.getFondos().stream().filter(fondo -> fondo.getFondoId().equals(transaccion.getFondoId())).findFirst().orElse(null);
            if (fondoCliente == null) {
                return;
            }
            cliente.getFondos().remove(fondoCliente);
            clienteService.actualizarCliente(cliente);
        }
    }


}
