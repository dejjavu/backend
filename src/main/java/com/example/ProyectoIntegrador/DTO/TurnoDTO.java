package com.example.ProyectoIntegrador.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDTO {


    private Long id;
    private String fechaAltaTurno;
    private String fechaDeTurno;
    private String hora;
    private String pacienteNombre;
    private String pacienteApellido;
    private Long pacienteId;
    private String odontologoNombre;
    private String odontologoApellido;
    private Long odontologoId;


}
