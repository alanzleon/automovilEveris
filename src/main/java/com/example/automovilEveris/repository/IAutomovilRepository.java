package com.example.automovilEveris.repository;

import com.example.automovilEveris.entity.Automovil;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface IAutomovilRepository extends MongoRepository<Automovil, Serializable> {

    Automovil findByPatente (String patente);

    @Query("{'estadoArriendo':?0}")
    List<Automovil> findAutomovilesByEstadoArriendo (String estadoArriendo);


}
