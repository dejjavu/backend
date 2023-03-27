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
        return odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo(Long id) {
        if (odontologoRepository.findById(id).isPresent()) {
            odontologoRepository.deleteById(id);
        }
    }
    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        if (odontologoRepository.findById(odontologo.getId()).isPresent()) {
            return odontologoRepository.save(odontologo);
        }
        return new Odontologo();
    }
    public Odontologo buscarOdontologo(Long id) {
        if (odontologoRepository.findById(id).isPresent()) {
            return odontologoRepository.findById(id).get();
        }
        return new Odontologo();
    }
    public Iterable<Odontologo> findAll() {
        return odontologoRepository.findAll();
    }
}
