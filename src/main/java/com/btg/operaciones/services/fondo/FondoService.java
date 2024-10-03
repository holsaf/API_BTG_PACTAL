package com.btg.operaciones.services.fondo;

import com.btg.operaciones.entities.Fondo;

public interface FondoService {

    Fondo consultarFondo(String id);

    void actualizarFondo(Fondo fondo);


}
