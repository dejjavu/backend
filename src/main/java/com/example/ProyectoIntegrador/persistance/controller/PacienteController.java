package com.example.ProyectoIntegrador.persistance.controller;

import com.example.ProyectoIntegrador.entities.Paciente;
import com.example.ProyectoIntegrador.entities.Turno;
import com.example.ProyectoIntegrador.persistance.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    @Autowired
    private final PacienteService pacienteService;


    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPaciente(id));
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPaciente(@PathVariable Long id) {
        Long odontologoId = pacienteService.buscarPaciente(id).getId();
        if (odontologoId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Paciente con el ID " + id + " no existe");
        } else {
            try {
                pacienteService.eliminarOdontologo(id);
                return ResponseEntity.status(HttpStatus.OK).body("El Paciente con el ID " + id + " ha sido eliminado correctamente");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al eliminar el Paciente con el ID " + id);
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPaciente(){
        return ResponseEntity.ok(pacienteService.findAll());
    }

    @GetMapping("/{id}/turnos")
    public ResponseEntity<List<Turno>> turnosPorIdPaciente(@PathVariable Long id) {
        List<Turno> turnos = pacienteService.turnosPorIdPaciente(id);
        return ResponseEntity.ok(turnos);
    }
}