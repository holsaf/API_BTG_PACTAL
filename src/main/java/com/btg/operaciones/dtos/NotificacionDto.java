package com.btg.operaciones.dtos;

import com.btg.operaciones.entities.Cliente;
import lombok.Data;

@Data
public class NotificacionDto {

    private Cliente cliente;
    private String nombreFondo;
    private String tipoTransaccion;
    private String tipoNotificacion;

    public NotificacionDto(Cliente cliente, String nombreFondo, String tipoTransaccion, String tipoNotificacion) {
        this.cliente = cliente;
        this.nombreFondo = nombreFondo;
        this.tipoTransaccion = tipoTransaccion;
        this.tipoNotificacion = tipoNotificacion;
    }

}
