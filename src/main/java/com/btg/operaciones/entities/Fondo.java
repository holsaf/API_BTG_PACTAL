package com.btg.operaciones.entities;


import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "fondos")
public class Fondo {
    @Id
    private String id;
    private String nombre;
    private double montoMinimoVinculacion;
    private double saldoTotal;

}
