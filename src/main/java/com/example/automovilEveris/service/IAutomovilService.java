package com.example.automovilEveris.service;

import com.example.automovilEveris.entity.Automovil;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IAutomovilService {

    String saveAutomovil (Automovil auto);
    List<Automovil> findAutomoviles ();
    void updateAuto (Automovil auto, String id);
    Optional<Automovil> findAutoById(String id);
}
