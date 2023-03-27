package com.example.ProyectoIntegrador.persistance.controller;

import com.example.ProyectoIntegrador.entities.Turno;
import com.example.ProyectoIntegrador.persistance.repository.TurnoRepository;
import com.example.ProyectoIntegrador.persistance.service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(TurnoController.class);
    private final TurnoRepository turnoRepository;

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.buscarTurno(id));
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.actualizarTurno(turno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eliminado");
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listaTurnos(){
        return ResponseEntity.ok(turnoService.findAll());
    }

}
