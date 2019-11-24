package com.example.automovilEveris.service;

import com.example.automovilEveris.entity.Automovil;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IAutomovilService {

    String guardarAutomovil (Automovil auto);
    List<Automovil> obtenerAutomoviles ();
    //String updateAuto ( String patente,Automovil auto);
    Automovil findAutoByPatente(String id);
    String updateAuto(String param, String patente);
    List<Automovil> findAutosByEstadoArriendo(String status);


}
