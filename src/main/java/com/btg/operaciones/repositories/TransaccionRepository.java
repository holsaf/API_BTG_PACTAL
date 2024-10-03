package com.btg.operaciones.repositories;

import com.btg.operaciones.entities.Transaccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepository extends MongoRepository<Transaccion, String> {

    List<Transaccion> findByClienteId(String clienteId);

    @Query("{ 'clienteId': ?0, 'fechaDesde': { $gte: ?1 } }")
    Page<Transaccion> findByClienteIdAndFechaDesde(String clienteId, String fechaDesde, Pageable page);

}
