package com.example.ProyectoIntegrador.persistance.controller;
import com.example.ProyectoIntegrador.entities.Paciente;
import com.example.ProyectoIntegrador.persistance.repository.PacienteRepository;
import com.example.ProyectoIntegrador.persistance.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Collections.singletonList;


@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor

public class PacienteController {

    @Autowired
    private  PacienteService pacienteService;
    @Autowired
    private  PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<Object> guardarPaciente(@RequestBody Paciente paciente) throws Exception {
        try {
            if (paciente.getNombre() == null || paciente.getApellido() == null || paciente.getDni() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor ingrese todos los datos correctamente.");
            }
            if (pacienteService.buscarPacientePorDNI(paciente.getDni()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un paciente con ese DNI.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(pacienteService.guardarPaciente(paciente));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al procesar la solicitud " + e+".");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPaciente(@PathVariable Long id) {
        try {
            Long pacienteid = pacienteService.buscarPaciente(id).getId();
            if (pacienteid == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El paciente con el ID " + id + " no existe");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(pacienteService.buscarPaciente(id));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al procesar la solicitud");
        }
    }


    @PutMapping
    public ResponseEntity<Object> actualizarPaciente(@RequestBody Paciente paciente) {
        try {
            if (paciente.getDni() == null || paciente.getDni().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El DNI es obligatorio.");
            }

            Paciente pacienteExistente = pacienteService.buscarPacientePorDNI(paciente.getDni());
            if (pacienteExistente != null && pacienteExistente.getId() != paciente.getId()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El DNI ya fue asignado a otro Paciente");
            }

            Paciente pacienteActualizado = pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok(pacienteActualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al procesar la solicitud");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPaciente(@PathVariable Long id) {
        Long pacienteId = pacienteService.buscarPaciente(id).getId();
        if (pacienteId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El paciente con el ID " + id + " no existe");
        } else {
            try {
                pacienteService.eliminarPaciente(id);
                return ResponseEntity.status(HttpStatus.OK).body("El paciente con el ID " + id + " ha sido eliminado correctamente");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al eliminar el paciente con el ID " + id);
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> listarPaciente() {
        try {
            pacienteService.listarPaciente();
            return ResponseEntity.status(HttpStatus.OK).body(pacienteService.listarPaciente());
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(singletonList("Ha ocurrido un error al procesar la solicitud"));
            throw new RuntimeException("Error al listar pacientes", e);
        }
    }


}

