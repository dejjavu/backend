package com.example.ProyectoIntegrador.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;


}

