package com.btg.operaciones.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClientePostDto {

    @NotEmpty
    @Size(min = 3, max = 50)
    private String nombreCliente;

    @NotEmpty
    @Email
    private String correoElectronico;

    //validate international phone number
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$")
    private String numeroCelular;

    @DecimalMin("500000.00")
    private double valorSaldo;

}
