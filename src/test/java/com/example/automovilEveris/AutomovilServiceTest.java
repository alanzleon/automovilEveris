package com.example.automovilEveris.service;

import com.example.automovilEveris.controller.AutomovilController;
import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.repository.IAutomovilRepository;
import com.example.automovilEveris.service.AutomovilService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutomovilServiceTest {
    @InjectMocks
    private AutomovilService autoService;
    @Mock
    private IAutomovilRepository repository;


    //private AutomovilService autoService = new AutomovilService();

    //@Ignore
    @Test
    public void obtenerAutomovilesTest() {
        Automovil auto1 =new Automovil();
        Date fecha = new Date(2019-12-01);

        auto1.setId("yfdaufdfa");
        auto1.setPatente("WWWW44");
        auto1.setMarca("fiat");
        auto1.setModelo("pp");
        auto1.setColor("azul");
        auto1.setEstadoArriendo("Disponible");
        auto1.setFabricado(2017);
        auto1.setFechaVctoRevision(fecha);
        auto1.setNumAsientos(4);
        auto1.setNumRevisionTecnica("bdiahdoahdo");
        auto1.setTipo("SUV");
        auto1.setValorDiario(25000);
        List<Automovil> autos = new ArrayList<>();

        autos.add(auto1);
        Mockito.when(repository.findAll()).thenReturn(autos);
        Assert.assertTrue( autoService.obtenerAutomoviles().size()>0);
    }
}
