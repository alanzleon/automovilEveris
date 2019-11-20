package com.example.automovilEveris.service;

import com.example.automovilEveris.entity.Automovil;

import java.util.List;
import java.util.Optional;

public interface IAutomovilService {

    void saveAutomovil (Automovil auto);
    List<Automovil> findAutomoviles ();
    void updateAuto (Automovil auto, String id);
    Optional<Automovil> findAutoById(String id);
}
