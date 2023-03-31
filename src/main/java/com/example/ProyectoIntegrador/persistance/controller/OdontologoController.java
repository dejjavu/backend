package com.example.ProyectoIntegrador.persistance.controller;

import com.example.ProyectoIntegrador.entities.Odontologo;
import com.example.ProyectoIntegrador.entities.Paciente;
import com.example.ProyectoIntegrador.entities.Turno;
import com.example.ProyectoIntegrador.persistance.repository.OdontologoRepository;
import com.example.ProyectoIntegrador.persistance.service.OdontologoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/odontologos")


public class OdontologoController {

    @Autowired
    private final OdontologoService odontologoService;
    @Autowired
    private final OdontologoRepository odontologoRepository;

    @PostMapping
    public ResponseEntity<Object> guardarOdontologo(@RequestBody Odontologo odontologo) throws Exception {
        try {
            if (odontologo.getNombre() == null || odontologo.getApellido() == null || odontologo.getMatricula() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor ingrese todos los datos correctamente.");
            }
            if (odontologoService.buscarOdontologoPorMatricula(odontologo.getMatricula()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un odontólogo con esa matrícula.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(odontologoService.guardarOdontologo(odontologo));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al procesar la solicitud ");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarOdontologo(@PathVariable Long id) {
        try {
            Long odontologoid = odontologoService.buscarOdontologo(id).getId();
            if (odontologoid == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El odontólogo con el ID " + id + " no existe");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(odontologoService.buscarOdontologo(id));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al procesar la solicitud");
        }
    }


    @PutMapping
    public ResponseEntity<Object> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        try {
            if (odontologo.getMatricula() == null || odontologo.getMatricula().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La matrícula es obligatoria");
            }

            Odontologo odontologoExistente = odontologoService.buscarOdontologoPorMatricula(odontologo.getMatricula());
            if (odontologoExistente != null && odontologoExistente.getId() != odontologo.getId()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La matrícula ya fue asignada a otro odontólogo");
            }

            Odontologo odontologoActualizado = odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok(odontologoActualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al procesar la solicitud");
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarOdontologo(@PathVariable Long id) {
        Long odontologoId = odontologoService.buscarOdontologo(id).getId();
        if (odontologoId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El odontólogo con el ID " + id + " no existe");
        } else {
            try {
                odontologoService.eliminarOdontologo(id);
                return ResponseEntity.status(HttpStatus.OK).body("El odontólogo con el ID " + id + " ha sido eliminado correctamente");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al eliminar el odontólogo con el ID " + id);
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> listarOdontologo() {
        try {
            odontologoService.listarOdontologo();
            return ResponseEntity.status(HttpStatus.OK).body(odontologoService.listarOdontologo());
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList("Ha ocurrido un error al procesar la solicitud"));
            throw new RuntimeException("Error al listar odontólogos");
        }
    }


}
