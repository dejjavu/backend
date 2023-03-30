package com.example.ProyectoIntegrador.persistance.service;

import com.example.ProyectoIntegrador.DTO.TurnoDTO;
import com.example.ProyectoIntegrador.entities.Turno;
import com.example.ProyectoIntegrador.persistance.repository.TurnoRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@Log4j

public class TurnoService {

    private final TurnoRepository  turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno guardarTurno(Turno turno) throws Exception {

        Turno turnoGuardado;
        try {
            turnoGuardado = turnoRepository.save(turno);
            log.info("Turno guardado con éxito " + turnoGuardado);
        } catch (Exception e) {
            log.error("Se produjo un error");
            throw new Exception("Error al guardar Turno.");
        }
        return turnoGuardado;
    }

    public void eliminarTurno(Long id) throws Exception {
        try {
            if (turnoRepository.findById(id).isPresent()){
                turnoRepository.deleteById(id);
                log.info("Turno ID " + id +" eliminado con éxito.");
            }
        } catch (Exception e){
            log.error("No se pudo eliminar el turno ID " + id);
            throw new Exception("Error al eliminar Turno.");
        }
    }

    public Turno actualizarTurno(Turno turno) throws Exception {
        if (turnoRepository.findById(turno.getId()).isPresent()) {
            log.info("Turno ID " + turno.getId() +" actualizado con éxito.");
            return (turnoRepository.save(turno));
        }
        throw new Exception("Turno no encontrado");
    }

    public TurnoDTO buscarTurno(Long id) throws Exception {
        if (turnoRepository.findById(id).isPresent()) {
            return toDTO(turnoRepository.findById(id).get());
        }
                throw new Exception("Turno no encontrado");
    }

    public List<TurnoDTO> listarTurnos() {
        return toDTOList(turnoRepository.findAll());
    }

    private Turno toEntity(TurnoDTO turnoDTO) {
        Turno turno = new Turno();
        turno.setId(turnoDTO.getId());
        turno.setFechaAltaTurno(LocalDate.parse(turnoDTO.getFechaAltaTurno()));
        turno.setFechaDeTurno(LocalDate.parse(turnoDTO.getFechaDeTurno()));
        return turno;
    }

    private TurnoDTO toDTO(Turno turno) {
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFechaAltaTurno(turno.getFechaAltaTurno().toString());
        turnoDTO.setFechaDeTurno(turno.getFechaDeTurno().toString());
        turnoDTO.setHora(turno.getHoraDeTurno().toString());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        turnoDTO.setPacienteNombre(turno.getPaciente().getNombre());
        turnoDTO.setPacienteApellido(turno.getPaciente().getApellido());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());
        turnoDTO.setOdontologoNombre(turno.getOdontologo().getNombre());
        turnoDTO.setOdontologoApellido(turno.getOdontologo().getApellido());
        return turnoDTO;
    }

    private List<TurnoDTO> toDTOList(List<Turno> turnos) {
        List<TurnoDTO> turnosDTO = new ArrayList<>();
        for (Turno turno : turnos) {
            turnosDTO.add(toDTO(turno));
        }
        return turnosDTO;
    }
}
