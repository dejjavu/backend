package com.example.ProyectoIntegrador.persistance.service;

import com.example.ProyectoIntegrador.entities.Odontologo;
import com.example.ProyectoIntegrador.persistance.repository.OdontologoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OdontologoService {
    private final OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo(Odontologo odontologo) {

        Odontologo odontologoAGuardar = odontologoRepository.save(odontologo);
        return odontologoAGuardar;
    }
}