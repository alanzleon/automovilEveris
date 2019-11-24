package com.example.automovilEveris.service;

import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.repository.IAutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        if(this.repository.findByPatente(auto.getPatente()) == null) {
            if (auto.getFabricado() >= 2000 && auto.getFabricado() < 2020) {
                if (auto.getMarca() != null && auto.getModelo() != null && auto.getNumRevisionTecnica() != null && auto.getFechaVctoRevision() != null && auto.getValorDiario() != 0.0) {
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
                                response = "numero de asientos entre 2 - 9";
                            }
                        } else {
                            response = "estado de automovil no valido";
                        }
                    } else {
                        response = "tipo de auto no valido";
                    }
                } else {
                    response = "complete todos los campos";
                }
            } else {
                response = "No se puede ingresar un vehiculo anterior al 2000";
            }
        }
        else {response="patente se encuentra registrada";}
        return response;
    }


    @Override
    public List<Automovil> obtenerAutomoviles() {
        return this.repository.findAll();
    }

/*
    public String actualizarAuto(String patente, String param) {
        Automovil autoEnBd = this.repository.findByPatente(patente);
           if (autoEnBd!=null){
            this.repository.UpdateAutomovilStatus(param);
            return "Actualizado";
        }
        else{
               return "No fue actualizado";
           }

    }
    */
@Override
public String actualizarAuto(Automovil auto, String patente) {
    //Valido que exista
    if(this.repository.findByPatente(patente) != null){
        Automovil autobd = this.repository.findByPatente(patente);
        autobd.setPatente(patente);

        if(auto.getMarca() != null) {
            autobd.setMarca(auto.getMarca());
        }
        if(auto.getModelo() != null) {
            autobd.setModelo(auto.getModelo());
        }
        if(auto.getColor() != null) {
            autobd.setColor(auto.getColor());
        }
        if(auto.getFabricado() !=0 ) {
            if (auto.getFabricado() > 2000 && auto.getFabricado()<=2019) {
                autobd.setFabricado(auto.getFabricado());
            }else{
                return "año fabricacion no valido";
            }
        }
        if(auto.getTipo() !=null) {
            if(auto.getTipo().toUpperCase().equals("CITYCAR") || auto.getTipo().toUpperCase().equals("SUV")||
            auto.getTipo().toUpperCase().equals("DESCAPOTABLE")|| auto.getTipo().toUpperCase().equals("ALTAGAMA")) {
                autobd.setTipo(auto.getTipo());
            }else {
                return "tipo no valido";
            }
        }
        if(auto.getNumAsientos() !=0) {
            if(auto.getNumAsientos()>=2 && auto.getNumAsientos()<10) {
                autobd.setNumAsientos(auto.getNumAsientos());
            }else{
                return "numero de asientos no valido";
            }
        }
        if(auto.getNumRevisionTecnica() !=null) {
            autobd.setNumRevisionTecnica(auto.getNumRevisionTecnica());
        }
        if(auto.getFechaVctoRevision() !=null) {
            autobd.setFechaVctoRevision(auto.getFechaVctoRevision());
        }
        if(auto.getEstadoArriendo() !=null) {
            if(auto.getEstadoArriendo().toUpperCase().equals("ARRENDADO")||auto.getEstadoArriendo().toUpperCase().equals("DISPONIBLE")||
                    auto.getEstadoArriendo().toUpperCase().equals("MANTENCION")) {
                autobd.setEstadoArriendo(auto.getEstadoArriendo());
            }else {
                return "estado no valido";
            }
        }
        if(auto.getValorDiario() !=0) {
            autobd.setValorDiario(auto.getValorDiario());
        }
        this.repository.save(autobd);
        return "registro actualizado";
    } else {
        return "no encontrado";
    }
}


    @Override
    public Automovil findAutoByPatente(String patente) {
        return this.repository.findByPatente(patente);

    }

    @Override
    public void eliminarAutomovil(String patente){
        this.repository.deleteById(patente);
    }


    @Override
    public List<Automovil> findAutosByEstadoArriendo(String status) {
        return this.repository.findAutomovilesByEstadoArriendo(status);
    }




    //cortesia de Lisandro
    private boolean isPatternMatcher(String exp, String val) throws NullPointerException {
        Pattern p = Pattern.compile(exp);
        Matcher m = p.matcher(val);
        return m.matches();
    }
}
