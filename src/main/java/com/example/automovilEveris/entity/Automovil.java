package com.example.automovilEveris.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Data
@Document(value = "automoviles")
public class Automovil {
    @Id
    @NotNull
    private String id;
    @NotNull
    private String patente;
    @NotNull
    private String marca;
    @NotNull
    private String modelo;
    @NotNull
    private String color;
    @NotNull
    private int fabricado;
    @NotNull
    private String tipo;//{Citycar, Descapotable, SUV, AltaGama}
    @NotNull
    private int numAsientos;
    @NotNull
    private String numRevisionTecnica;
    @NotNull
    private Date fechaVctoRevision;
    @NotNull
    private String estadoArriendo;//{Disponible, Mantencion, Arrendado}
    @NotNull
    private float valorDiario;


}
