package com.btg.operaciones.entities;


import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "fondos")
public class Fondo {
    @Id
    private String id;
    private String nombre;
    private String tipoFondo;
    private double montoMinimoApertura;
    private double saldoTotal;

}
