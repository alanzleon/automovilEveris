package com.example.automovilEveris.controller;

import com.example.automovilEveris.controller.AutomovilController;
import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.service.AutomovilService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutomovilControllerTest {

    @InjectMocks
    private AutomovilController controller;
    @Mock
    private AutomovilService autoService;

    @Before
    public void setUp() {
        //autoService= Mockito.mock(AutomovilService.class);
       //controller = new AutomovilController();
    }

    @Test
    //@Ignore
    public void obtenerTodosTest() {
        Automovil auto1 =new Automovil();
        Automovil auto2 =new Automovil();

        Date fecha = new Date(2019-12-01);
  //      auto1.setId("yfdaufdfa");
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

//        auto2.setId("8585858");
        auto2.setPatente("WWWW44");
        auto2.setMarca("fiat");
        auto2.setModelo("pp");
        auto2.setColor("azul");
        auto2.setEstadoArriendo("Disponible");
        auto2.setFabricado(2017);
        auto2.setFechaVctoRevision(fecha);
        auto2.setNumAsientos(4);
        auto2.setNumRevisionTecnica("bdiahdoahdo");
        auto2.setTipo("SUV");
        auto2.setValorDiario(25000);
        List<Automovil> autos = new ArrayList<>(
        );
        autos.add(auto1);
        autos.add(auto2);
        Mockito.when(autoService.obtenerAutomoviles()).thenReturn(autos);
        ResponseEntity<?> httpResponse = (ResponseEntity<?>) controller.obtenerTodos();

        Assert.assertEquals(200,httpResponse.getStatusCodeValue());
        //Assert.assertEquals(autos, httpResponse.getBody());
    }


    @Test
    //@Ignore
    public void getAutomovilByPatenteTest() {
        Automovil auto1 =new Automovil();
        auto1.setPatente("WWWW44");

        Mockito.when(autoService.findAutoByPatente("WWWW44")).thenReturn(auto1);
        ResponseEntity<?> httpResponse = (ResponseEntity<?>) controller.getAutomovilByPatente("WWWW44");

        Assert.assertEquals(200, httpResponse.getStatusCodeValue());
       // Assert.assertEquals(auto1, httpResponse.getBody());
    }

    @Test
    public void actualizarAutomovilTest(){
        Date fecha = new Date(2019-12-01);

        Automovil auto1 =new Automovil();
        auto1.setPatente("WWWW44");
        auto1.setMarca("fiat");
        auto1.setModelo("pp");
        auto1.setColor("azul");
        auto1.setEstadoArriendo("Disponible");
        auto1.setFabricado(2020);
        auto1.setFechaVctoRevision(fecha);
        auto1.setNumAsientos(4);
        auto1.setNumRevisionTecnica("bdiahdoahdo");
        auto1.setTipo("SUV");
        auto1.setValorDiario(25000);

        Mockito.when(autoService.actualizarAuto(auto1, "WWWW44")).thenReturn("año fabricacion no valido");
        ResponseEntity<?> testResponse =  controller.actualizar(auto1, "WWWW44");

        Assert.assertEquals( "{\"Año de fabricacion no valido\"}", testResponse.getBody());
    }
}
