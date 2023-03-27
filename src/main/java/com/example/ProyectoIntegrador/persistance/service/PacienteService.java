package com.example.ProyectoIntegrador.persistance.service;

import com.example.ProyectoIntegrador.entities.Paciente;
import com.example.ProyectoIntegrador.persistance.repository.DomicilioRepository;
import com.example.ProyectoIntegrador.persistance.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final DomicilioRepository domicilioRepository;
    private final PacienteRepository pacienteRepository;
    public Paciente guardarPaciente(Paciente paciente) {
        domicilioRepository.save(paciente.getDomicilio());
        return pacienteRepository.save(paciente);
    }
    public void eliminarPaciente(Long id) {
        if (pacienteRepository.findById(id).isPresent()) {
            pacienteRepository.deleteById(id);
        }
    }

    /////REVISAR ESTO
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
}
