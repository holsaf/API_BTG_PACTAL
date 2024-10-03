package com.btg.operaciones.models;

import com.btg.operaciones.enums.TipoNotificacionEnum;
import com.btg.operaciones.enums.TipoTransaccionEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
public class TransaccionRequest {

    @NotEmpty
    private String idCliente;

    @NotEmpty
    private String idFondo;

    private TipoTransaccionEnum tipoTransaccion; // "apertura" o "cancelacion"

    private TipoNotificacionEnum tipoNotificacion; // "email" o "sms"

    @DecimalMin("500000.00")
    private double valorMonto;

    private Date fechaTransaccion;

}
