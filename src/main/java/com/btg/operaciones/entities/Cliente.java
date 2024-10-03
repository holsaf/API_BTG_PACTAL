package com.btg.operaciones.entities;



import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;

    private String nombre;

    @NotEmpty
    private String email;

    //validate international phone number
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$")
    private String telefono;

    @DecimalMin("500000.00")
    private double saldo;

}