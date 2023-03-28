package com.example.ProyectoIntegrador.persistance.service;
import com.example.ProyectoIntegrador.entities.Odontologo;
import com.example.ProyectoIntegrador.persistance.repository.OdontologoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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
        return odontologo;
    }
    public Odontologo buscarOdontologo(Long id) {
        if (odontologoRepository.findById(id).isPresent()) {
            return odontologoRepository.findById(id).get();
        }
        return new Odontologo();
    }
    public List<Odontologo> listarOdontologo() {
        return odontologoRepository.findAll();
    }
}
