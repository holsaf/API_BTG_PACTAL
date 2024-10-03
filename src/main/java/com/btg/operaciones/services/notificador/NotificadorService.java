package com.btg.operaciones.services.notificador;

import com.btg.operaciones.entities.Transaccion;
import com.btg.operaciones.models.Notificacion;

public interface NotificadorService {
    void notificar(Notificacion notificacion);

}
