package com.example.ProyectoIntegrador.entities;

import com.example.ProyectoIntegrador.DTO.TurnoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    private String matricula;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Turno> turnos = new ArrayList<>();


}
