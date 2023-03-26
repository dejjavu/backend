package com.example.ProyectoIntegrador.persistance.service;

import com.example.ProyectoIntegrador.entities.Paciente;
import com.example.ProyectoIntegrador.persistance.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente) {

        Paciente pacienteAGuardar = pacienteRepository.save(paciente);
        return pacienteAGuardar;
    }


}
