package com.example.automovilEveris.service;

import com.example.automovilEveris.entity.Automovil;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IAutomovilService {

    String guardarAutomovil (Automovil auto);
    List<Automovil> obtenerAutomoviles ();
    Automovil findAutoByPatente(String id);
    String actualizarAuto(Automovil auto, String patente);
    List<Automovil> findAutosByEstadoArriendo(String status);
    void eliminarAutomovil(String patente);


}
