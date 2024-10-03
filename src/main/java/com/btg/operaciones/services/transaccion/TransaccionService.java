package com.btg.operaciones.services.transaccion;

import com.btg.operaciones.entities.Transaccion;
import com.btg.operaciones.dtos.TransaccionPostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransaccionService {

    Page<Transaccion> consultarTransaccionesByClienteAndFechaDesde(String clienteId, String fechaDesde, Pageable page);

    Transaccion guardarTransaccion(TransaccionPostDto request);


}
