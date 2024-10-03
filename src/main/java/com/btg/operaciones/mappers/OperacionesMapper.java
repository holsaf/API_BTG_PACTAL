package com.btg.operaciones.mappers;

import com.btg.operaciones.dtos.ClientePostDto;
import com.btg.operaciones.dtos.FondoPostDto;
import com.btg.operaciones.dtos.TransaccionPostDto;
import com.btg.operaciones.entities.Cliente;
import com.btg.operaciones.entities.Fondo;
import com.btg.operaciones.entities.Transaccion;
import org.springframework.stereotype.Component;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public interface OperacionesMapper {

    Fondo fondoPostDtoToFondo(FondoPostDto fondoPostDto);

    FondoPostDto fondoToFondoPostDto(Fondo fondo);

    Cliente clientePostDtoToCliente(ClientePostDto clientePostDto);

    ClientePostDto clienteToClientePostDto(Cliente cliente);

    Transaccion transaccionPostDtoToTransaccion(TransaccionPostDto transaccionPostDto);

    TransaccionPostDto transaccionToTransaccionPostDto(Transaccion transaccion);



}
