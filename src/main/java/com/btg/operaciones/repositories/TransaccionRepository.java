package com.btg.operaciones.repositories;

import com.btg.operaciones.entities.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TransaccionRepository extends MongoRepository<Transaccion, String> {
    List<Transaccion> findByClienteId(String clienteId);

}
