package com.btg.operaciones.repositories;

import com.btg.operaciones.entities.Fondo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FondoRepository extends MongoRepository<Fondo, String> {
}
