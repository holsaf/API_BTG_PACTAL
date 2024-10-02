package com.btg.operaciones.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Document(collection = "transacciones")
public class Transaccion {
    @Id
    private String id;
    private UUID uniqueId;
    private String clienteId;
    private String fondoId;
    private String tipo; // "apertura" o "cancelacion"
    private double monto;
    private Date fecha;


}