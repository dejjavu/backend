package com.example.ProyectoIntegrador.persistance.service;

import com.example.ProyectoIntegrador.entities.Odontologo;
import com.example.ProyectoIntegrador.entities.Paciente;
import com.example.ProyectoIntegrador.entities.Turno;
import com.example.ProyectoIntegrador.persistance.repository.DomicilioRepository;
import com.example.ProyectoIntegrador.persistance.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
@RequiredArgsConstructor
public class PacienteService {
    @Autowired
    private final DomicilioRepository domicilioRepository;
    @Autowired
    private final PacienteRepository pacienteRepository;
    public Paciente guardarPaciente(Paciente paciente) {
        domicilioRepository.save(paciente.getDomicilio());

        return pacienteRepository.save(paciente);
    }
    public void eliminarOdontologo(Long id) throws Exception {
        Odontologo odontologoEliminado;
        try {
            if (pacienteRepository.findById(id).isPresent()){
                pacienteRepository.deleteById(id);
                log.info("Paciente ID " + id +" paciente con éxito.");
            }
        }catch (Exception e){
            log.error("No se pudo eliminar el Paciente ID " + id);
        }
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        if (pacienteRepository.findById(paciente.getId()).isPresent()) {
            domicilioRepository.save(paciente.getDomicilio());
            return pacienteRepository.save(paciente);
        }
        return new Paciente();
    }
    public Paciente buscarPaciente(Long id) {
        if (pacienteRepository.findById(id).isPresent()) {
            return pacienteRepository.findById(id).get();
        }
        return new Paciente();
    }
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }



    public List<Turno> turnosPorIdPaciente(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
        return paciente.getTurnos();
    }


}
