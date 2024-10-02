package com.btg.operaciones.services;

import com.btg.operaciones.entities.Fondo;
import com.btg.operaciones.repositories.FondoRepository;
import org.springframework.stereotype.Service;

@Service
public class FondoService {

    private final FondoRepository fondoRepository;

    public FondoService(FondoRepository fondoRepository){
        this.fondoRepository = fondoRepository;
    }

    public Fondo consultarFondo(String fondoId) {
        return fondoRepository.findById(fondoId).orElse(null);
    }

    public void actualizarFondo(Fondo fondo) {
        var fondoActual = fondoRepository.findById(fondo.getId()).orElse(null);
        if (fondoActual == null) {
            return;
        }
        fondoActual.setSaldoTotal(fondo.getSaldoTotal());
        fondoActual.setNombre(fondo.getNombre());
        fondoActual.setMontoMinimoVinculacion(fondo.getMontoMinimoVinculacion());
        fondoRepository.save(fondo);

    }
}
