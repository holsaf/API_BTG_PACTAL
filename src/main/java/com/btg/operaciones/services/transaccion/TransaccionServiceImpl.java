package com.btg.operaciones.services.transaccion;

import com.btg.operaciones.entities.Cliente;
import com.btg.operaciones.entities.Fondo;
import com.btg.operaciones.entities.Transaccion;
import com.btg.operaciones.dtos.NotificacionDto;
import com.btg.operaciones.dtos.TransaccionPostDto;
import com.btg.operaciones.repositories.TransaccionRepository;
import com.btg.operaciones.services.notificador.NotificadorService;
import com.btg.operaciones.services.cliente.ClienteServiceImpl;
import com.btg.operaciones.services.fondo.FondoServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final FondoServiceImpl fondoService;
    private final ClienteServiceImpl clienteService;
    private final NotificadorService notificadorService;

    private Transaccion transaccion;


    public TransaccionServiceImpl(TransaccionRepository transaccionRepository, FondoServiceImpl fondoService, ClienteServiceImpl clienteService, NotificadorService notificadorService) {
        this.transaccionRepository = transaccionRepository;
        this.fondoService = fondoService;
        this.clienteService = clienteService;
        this.notificadorService = notificadorService;
    }

    public Page<Transaccion> consultarTransaccionesByClienteAndFechaDesde(String clienteId, String fechaDesde, Pageable page) {
        return transaccionRepository.findByClienteIdAndFechaDesde(clienteId, fechaDesde, page);
    }


    public Transaccion guardarTransaccion(TransaccionPostDto request) {
        this.transaccion = new Transaccion();
        this.transaccion.setTipoTransaccion(request.getTipoTransaccion().toString());
        this.transaccion.setTipoNotificacion(request.getTipoNotificacion().toString());
        this.transaccion.setClienteId(request.getIdCliente());
        this.transaccion.setFondoId(request.getIdFondo());
        this.transaccion.setMonto(request.getValorMonto());
        this.transaccion.setFecha(request.getFechaTransaccion());
        var cliente = clienteService.consultarCliente(transaccion.getClienteId());
        var fondo = fondoService.consultarFondo(transaccion.getFondoId());
        validacionesCliente(cliente, fondo);
        validacionesFondo(fondo);
        var fondoCreado = transaccionRepository.save(transaccion);
        actualizarSaldoFondo(fondo);
        actualizarSaldoCliente(cliente);
        envioNotificacion(cliente, fondo);
        return fondoCreado;
    }

    private void envioNotificacion(Cliente cliente, Fondo fondo) {
        NotificacionDto notificacionDto = new NotificacionDto(cliente, fondo.getNombre(), transaccion.getTipoTransaccion(), transaccion.getTipoNotificacion());
        notificadorService.notificar(notificacionDto);
    }


    private void validacionesFondo(Fondo fondo) {
        if (fondo == null) {
            throw new RuntimeException("Fondo no encontrado");
        }
        if (transaccion.getTipoTransaccion().equals("APERTURA") && fondo.getMontoMinimoVinculacion() > transaccion.getMonto()) {
            throw new RuntimeException("El monto mínimo de vinculación es de " + fondo.getMontoMinimoVinculacion());
        }
    }

    private void validacionesCliente(Cliente cliente, Fondo fondo) {
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }
        if (transaccion.getTipoTransaccion().equals("APERTURA") && cliente.getSaldo() < transaccion.getMonto()) {
            throw new RuntimeException("No tiene saldo disponible para vincularse al fondo " + fondo.getNombre());
        }
    }

    private void actualizarSaldoFondo(Fondo fondo) {
        if (transaccion.getTipoTransaccion().equals("APERTURA")) {
            fondo.setSaldoTotal(fondo.getSaldoTotal() + transaccion.getMonto());
            fondoService.actualizarFondo(fondo);
        } else if (transaccion.getTipoTransaccion().equals("CANCELAR")) {
            fondo.setSaldoTotal(fondo.getSaldoTotal() - transaccion.getMonto());
            fondoService.actualizarFondo(fondo);
        }
    }

    private void actualizarSaldoCliente(Cliente cliente) {
        if (transaccion.getTipoTransaccion().equals("APERTURA")) {
            cliente.setSaldo(cliente.getSaldo() - transaccion.getMonto());
            clienteService.actualizarCliente(cliente);
        } else if (transaccion.getTipoTransaccion().equals("CANCELAR")) {
            cliente.setSaldo(cliente.getSaldo() + transaccion.getMonto());
            clienteService.actualizarCliente(cliente);
        }
    }


}
