package com.example.automovilEveris.repository;

import com.example.automovilEveris.entity.Automovil;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IAutomovilRepository extends MongoRepository<Automovil, Serializable> {
}
