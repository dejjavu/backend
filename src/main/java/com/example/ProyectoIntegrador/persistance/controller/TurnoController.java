package com.example.ProyectoIntegrador.persistance.controller;
import com.example.ProyectoIntegrador.DTO.TurnoDTO;
import com.example.ProyectoIntegrador.entities.Turno;
import com.example.ProyectoIntegrador.persistance.repository.TurnoRepository;
import com.example.ProyectoIntegrador.persistance.service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(TurnoController.class);
    private final TurnoRepository turnoRepository;
    private TurnoDTO Turno;

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) throws Exception {
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTurno(@PathVariable Long id) throws Exception {
           if (turnoRepository.findById(id).isEmpty()){
            return ResponseEntity.badRequest().body("El Turno con el ID " + id +" no fue encontrado");
        }
        return ResponseEntity.ok((turnoService.buscarTurno(id)));
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) throws Exception {
        return ResponseEntity.ok(turnoService.actualizarTurno(turno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws Exception {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eliminado");
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listaTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

}
