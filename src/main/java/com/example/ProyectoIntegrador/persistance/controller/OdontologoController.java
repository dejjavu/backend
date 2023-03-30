package com.example.ProyectoIntegrador.persistance.controller;

import com.example.ProyectoIntegrador.DTO.TurnoDTO;
import com.example.ProyectoIntegrador.entities.Odontologo;
import com.example.ProyectoIntegrador.entities.Turno;
import com.example.ProyectoIntegrador.persistance.service.OdontologoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/odontologos")

public class OdontologoController {

    @Autowired
    private final OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) throws Exception {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarOdontologo(@PathVariable Long id) {
        try {
            Long odontologoid = odontologoService.buscarOdontologo(id).getId();
            if (odontologoid == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El odontólogo con el ID " + id + " no existe");
            } else {
                return ResponseEntity.ok(odontologoService.buscarOdontologo(id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al procesar la solicitud");
        }
    }


    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        try {
            return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        } catch (RuntimeException e) {
        }
        return ResponseEntity.ok(odontologo);
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
    public ResponseEntity<List<Odontologo>> listarOdontologo() {
        try {
            return ResponseEntity.ok(odontologoService.listarOdontologo());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar odontólogos", e);
        }
    }


    @GetMapping("/{id}/turnos")
    public ResponseEntity<List<TurnoDTO>> getTurnosByOdontologoId(@PathVariable Long id) {
        List<?> turnos = odontologoService.getTurnosByOdontologoId(id);
        return (ResponseEntity<List<TurnoDTO>>) ResponseEntity.ok();
    }


}
