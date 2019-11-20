package com.example.automovilEveris.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Automovil {
    private String marca;
    private String modelo;
    private String patente;
    private int fabricado;
    private String revisionTecnica;
    private String estado;
    private float valorDiario;

}
