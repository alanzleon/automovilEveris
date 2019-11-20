package com.example.automovilEveris.service;

import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.repository.IAutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AutomovilService implements IAutomovilService{
    @Autowired
    private IAutomovilRepository repository;

    @Override
    public void saveAutomovil(Automovil auto) {
        Automovil newAuto;
        if (auto.getFabricado()>=2000){
            newAuto=auto;
        }
        else if(auto.getFabricado()>=2000 && auto.getFabricado()<=2007){

        }
        else{}
    }

    @Override
    public List<Automovil> findAutomoviles() {
        return null;
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
}
