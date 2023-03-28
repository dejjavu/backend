package com.example.ProyectoIntegrador.persistance.service;

import com.example.ProyectoIntegrador.entities.Turno;

import com.example.ProyectoIntegrador.persistance.controller.TurnoController;
import com.example.ProyectoIntegrador.persistance.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class TurnoService {
    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(TurnoController.class);
    @Autowired
    private final TurnoRepository turnoRepository;
    @Autowired
    private final OdontologoService odontologoService;
    @Autowired
    private final PacienteService pacienteService;
    public Turno guardarTurno(Turno turno) {
        odontologoService.buscarOdontologo(turno.getOdontologo().getId());
        pacienteService.buscarPaciente(turno.getPaciente().getId());
        return turnoRepository.save(turno);
    }
    public void eliminarTurno(Long id) {
        if (turnoRepository.findById(id).isPresent()) {
            turnoRepository.deleteById(id);
        }
    }
    public Turno actualizarTurno(Turno turno) {
        if (turnoRepository.findById(turno.getId()).isPresent()) {
            return turnoRepository.save(turno);
        }
        return new Turno();
    }
    public Turno buscarTurno(Long id) {
        if (turnoRepository.findById(id).isPresent()) {
            return turnoRepository.findById(id).get();
        }
        return new Turno();
    }
    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }
}


