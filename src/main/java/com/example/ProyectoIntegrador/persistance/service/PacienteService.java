package com.example.ProyectoIntegrador.persistance.service;
import com.example.ProyectoIntegrador.entities.Paciente;
import com.example.ProyectoIntegrador.persistance.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log4j
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente) throws Exception {
        Paciente pacienteGuardado;
        try {
            log.info("Guardando Paciente.");
            pacienteGuardado = pacienteRepository.save(paciente);
            log.info("Paciente guardado con éxito " + pacienteGuardado);
        } catch (Exception e) {
            log.error("Se produjo un error");
            throw new Exception("Error al guardar Paciente.");
        }
        return pacienteGuardado;
    }


    public void eliminarPaciente(Long id) throws Exception {
        Paciente pacienteEliminado;
        try {
            if (pacienteRepository.findById(id).isPresent()){
                pacienteRepository.deleteById(id);
                log.info("Paciente ID " + id +" eliminado con éxito.");
            }
        }catch (Exception e){
            log.error("No se pudo eliminar el Paciente ID " + id);
        }
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        if (pacienteRepository.findById(paciente.getId()).isPresent()) {
            log.info("Paciente ID " + paciente.getId() +" actualizado con éxito.");
            return pacienteRepository.save(paciente);

        }
        return paciente;
    }

    public Paciente buscarPaciente(Long id) {
        if (pacienteRepository.findById(id).isPresent()) {
            return pacienteRepository.findById(id).get();
        }
        return new Paciente();
    }

    public List<Paciente> listarPaciente() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPacientePorDNI(String dni) {
        return pacienteRepository.findByDNI(dni);
    }
}

