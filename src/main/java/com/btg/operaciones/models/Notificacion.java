package com.btg.operaciones.models;

import com.btg.operaciones.entities.Cliente;
import lombok.Data;

@Data
public class Notificacion {

    private Cliente cliente;
    private String nombreFondo;
    private String tipoTransaccion;
    private String tipoNotificacion;

    public Notificacion(Cliente cliente, String nombreFondo, String tipoTransaccion, String tipoNotificacion) {
        this.cliente = cliente;
        this.nombreFondo = nombreFondo;
        this.tipoTransaccion = tipoTransaccion;
        this.tipoNotificacion = tipoNotificacion;
    }

}
