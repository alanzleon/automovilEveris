package com.example.automovilEveris.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Data
@Document(value = "automovil")
public class Automovil {
    @Id
    @NotNull
    private String id;
    @NotNull
    private String patente;

    private String marca;
    private String modelo;
    private String color;
    private int fabricado;
    private String tipo;//{citycar, grande, suv, altaGama}
    private boolean aireAcondicionado;
    private int numAsientos;
    private String numMotor;
    private String numChasis;
    private String numRevisionTecnica;
    private Date fechaVctoRevision;
    private String estadoArriendo;
    private float valorDiario;


}
