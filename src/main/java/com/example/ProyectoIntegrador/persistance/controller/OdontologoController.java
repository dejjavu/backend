package com.example.ProyectoIntegrador.persistance.controller;

import com.example.ProyectoIntegrador.entities.Odontologo;
import com.example.ProyectoIntegrador.persistance.service.OdontologoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdotonlogo(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarOdontologo(id));
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado");
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarPaciente(){
        return ResponseEntity.ok(odontologoService.listarOdontologo());
    }



}
