package com.example.automovilEveris;

import com.example.automovilEveris.controller.AutomovilController;
import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.service.AutomovilService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutomovilControllerTest {
    private AutomovilController controller;
    private AutomovilService autoService;

    @Before
    public void setUp() {
         autoService= Mockito.mock(AutomovilService.class);
        controller = new AutomovilController();
    }

    @Test
    public void obtenerTodosTest() {
        Automovil auto1 =new Automovil();
        Automovil auto2 =new Automovil();
        List<Automovil> autos = null;
        autos.add(auto1);
        autos.add(auto2);
        Mockito.when(autoService.obtenerAutomoviles()).thenReturn(autos);
        ResponseEntity<?> httpResponse = (ResponseEntity<?>) controller.obtenerTodos();

        Assert.assertEquals(200,httpResponse.getStatusCodeValue());
        //Assert.assertEquals(autos, httpResponse.getBody());
    }


    @Test
    public void getAutomovilByPatenteTest() {
        Automovil auto1 =new Automovil();
        auto1.setPatente("WWWW44");

        Mockito.when(autoService.findAutoByPatente("WWWW44")).thenReturn(auto1);
        ResponseEntity<?> httpResponse = (ResponseEntity<?>) controller.getAutomovilByPatente("WWWW44");

        Assert.assertEquals(200, httpResponse.getStatusCodeValue());
       // Assert.assertEquals(auto1, httpResponse.getBody());
    }
}
