package com.example.automovilEveris.service;

import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.repository.IAutomovilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AutomovilService implements IAutomovilService{
    @Autowired
    private IAutomovilRepository repository;

    @Override
    public String saveAutomovil(Automovil auto) {
        String response="";
        //Automovil newAuto;

                    if (auto.getFabricado() >= 2000) {

                        if (auto.getFabricado() <= 2007 && isPatternMatcher("[A-Z]{2}[1-9]{1}[0-9]{3}", auto.getPatente())) {
                            this.repository.save(auto);
                            response = "Automovil agregado!";
                        } else if (auto.getFabricado() > 2007 && isPatternMatcher("[BCDFGHJKLPRSTVWXYZ]{4}[0-9]{2}", auto.getPatente())) {
                            this.repository.save(auto);
                        } else {
                            response = "Formato de patente invalido";
                        }
                    } else {
                        response = "No se puede ingresar un vehiculo anterior al 2000";
                    }

    return response;
    }

    @Override
    public List<Automovil> findAutomoviles() {
        return this.repository.findAll();
    }

    @Override
    public void updateAuto(Automovil auto, String id) {
        auto.setId(id);
        this.repository.save(auto);
    }

    @Override
    public Optional<Automovil> findAutoById(String id) {
        return this.repository.findById(id);
    }
    //cortesia de Lisandro
    private boolean isPatternMatcher(String exp, String val) throws NullPointerException {
        Pattern p = Pattern.compile(exp);
        Matcher m = p.matcher(val);
        return m.matches();
    }
}
