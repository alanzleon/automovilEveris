package com.example.automovilEveris;

import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.service.AutomovilService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AutomovilServiceTest {
    private AutomovilService autoService = new AutomovilService();

    @Test
    public void obtnerAutomovilesTest() {
        Automovil auto1 =new Automovil();
        Automovil auto2 =new Automovil();
        List<Automovil> autos = null;
        autos.add(auto1);
        autos.add(auto2);
        Assert.assertEquals(autos, autoService.obtenerAutomoviles());
    }
}
