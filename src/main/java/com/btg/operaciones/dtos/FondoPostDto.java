package com.btg.operaciones.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FondoPostDto {

    @NotEmpty
    private String nombreFondo;

    @NotEmpty
    @Size(max = 3, message = "El tamaño del fondo debe ser maximo de 3 caracteres")
    private String tipoFondo;

    @DecimalMin("10000")
    private double montoMinimo;

}
