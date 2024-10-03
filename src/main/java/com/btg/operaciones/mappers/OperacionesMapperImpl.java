package com.btg.operaciones.mappers;

import com.btg.operaciones.dtos.ClientePostDto;
import com.btg.operaciones.dtos.FondoPostDto;
import com.btg.operaciones.dtos.TransaccionPostDto;
import com.btg.operaciones.entities.Cliente;
import com.btg.operaciones.entities.Fondo;
import com.btg.operaciones.entities.Transaccion;
import org.springframework.stereotype.Component;

@Component
public class OperacionesMapperImpl  implements OperacionesMapper {


    @Override
    public Fondo fondoPostDtoToFondo(FondoPostDto fondoPostDto) {
        if(fondoPostDto == null){
            return null;
        }
        Fondo fondo = new Fondo();
        fondo.setNombre(fondoPostDto.getNombreFondo());
        fondo.setMontoMinimoApertura(fondoPostDto.getMontoMinimo());
        return fondo;
    }

    @Override
    public FondoPostDto fondoToFondoPostDto(Fondo fondo) {
        return null;
    }

    @Override
    public Cliente clientePostDtoToCliente(ClientePostDto clientePostDto) {
        return null;
    }

    @Override
    public ClientePostDto clienteToClientePostDto(Cliente cliente) {
        return null;
    }

    @Override
    public Transaccion transaccionPostDtoToTransaccion(TransaccionPostDto transaccionPostDto) {
        return null;
    }

    @Override
    public TransaccionPostDto transaccionToTransaccionPostDto(Transaccion transaccion) {
        return null;
    }
}
