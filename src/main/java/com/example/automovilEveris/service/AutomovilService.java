package com.example.automovilEveris.service;

import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.repository.IAutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AutomovilService implements IAutomovilService{
    @Autowired
    private IAutomovilRepository repository;

    @Override
    public String guardarAutomovil(Automovil auto) {
        String response="";
                    if (auto.getFabricado() >= 2000 && auto.getFabricado()<2020) {
                        if(auto.getMarca()!=null&&auto.getModelo()!=null&&auto.getNumRevisionTecnica()!=null&&auto.getFechaVctoRevision()!=null&&auto.getValorDiario()!=0.0) {
                            if (auto.getTipo().toUpperCase().equals("CITYCAR") || auto.getTipo().toUpperCase().equals("SUV") || auto.getTipo().toUpperCase().equals("DESCAPOTABLE") ||
                                    auto.getTipo().toUpperCase().equals("ALTAGAMA")) {
                                if (auto.getEstadoArriendo().toUpperCase().equals("ARRENDADO") || auto.getEstadoArriendo().toUpperCase().equals("DISPONIBLE") ||
                                        auto.getEstadoArriendo().toUpperCase().equals("MANTENCION")) {
                                    if (auto.getNumAsientos() < 10 && auto.getNumAsientos() >= 2) {
                                        if (auto.getFabricado() <= 2007 && isPatternMatcher("[A-Z]{2}[1-9]{1}[0-9]{3}", auto.getPatente())) {
                                            this.repository.save(auto);
                                            response = "Automovil agregado!";
                                        } else if (auto.getFabricado() > 2007 && isPatternMatcher("[BCDFGHJKLPRSTVWXYZ]{4}[0-9]{2}", auto.getPatente())) {
                                            this.repository.save(auto);
                                            response = "Automovil agregado!";
                                        } else {
                                            response = "Formato de patente invalido";
                                        }
                                    } else {
                                        response = "numero de asientos invalido";
                                    }
                                } else {
                                    response = "estado de automovil no valido";
                                }
                            } else {
                                response = "tipo de auto no valido";
                            }

                        }
                        else {
                            response = "complete todos los campos";}
                    }
                    else { response = "No se puede ingresar un vehiculo anterior al 2000"; }
        return response;
    }

    //busca todos los autos
    @Override
    public List<Automovil> obtenerAutomoviles() {
        return this.repository.findAll();
    }

    //actualiza la info de un auto

    /*public String updateAuto(String patente, Automovil auto) {
        Automovil autoEnBd = this.repository.findByPatente(patente);
        this.repository.save(auto);
        return patente;
    }*/

    //encuentra un auto segun patente
    @Override
    public Automovil findAutoByPatente(String patente) {
        return this.repository.findByPatente(patente);

    }

    @Override
    public List<Automovil> findAutosByEstadoArriendo(String status) {
        return this.repository.findAutomovilesByEstadoArriendo(status);

    }



    //verifica formato de ingreso
    //cortesia de Lisandro
    private boolean isPatternMatcher(String exp, String val) throws NullPointerException {
        Pattern p = Pattern.compile(exp);
        Matcher m = p.matcher(val);
        return m.matches();
    }
}
