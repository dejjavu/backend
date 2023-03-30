package com.example.ProyectoIntegrador.persistance.service;
import com.example.ProyectoIntegrador.DTO.TurnoDTO;
import com.example.ProyectoIntegrador.entities.Odontologo;
import com.example.ProyectoIntegrador.entities.Turno;
import com.example.ProyectoIntegrador.persistance.repository.OdontologoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Log4j
@RequiredArgsConstructor
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo(Odontologo odontologo) throws Exception {
        Odontologo odontologoGuardado;
        try {
            log.info("Guardando Odontologo.");
            odontologoGuardado = odontologoRepository.save(odontologo);
            log.info("Odontologo guardado con éxito " + odontologoGuardado);
        } catch (Exception e) {
            log.error("Se produjo un error");
            throw new Exception("Error al guardar Odontologo.");
        }
       return odontologoGuardado;
    }


    public void eliminarOdontologo(Long id) throws Exception {
        Odontologo odontologoEliminado;
        try {
            if (odontologoRepository.findById(id).isPresent()){
                odontologoRepository.deleteById(id);
                log.info("Odontologo ID " + id +" eliminado con éxito.");
            }
        }catch (Exception e){
            log.error("No se pudo eliminar el Odontologo ID " + id);
        }
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        if (odontologoRepository.findById(odontologo.getId()).isPresent()) {
            log.info("Odontologo ID " + odontologo.getId() +" actualizado con éxito.");
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

    public List<?> getTurnosByOdontologoId(Long odontologoId) {
        Odontologo odontologo = odontologoRepository.findById(odontologoId).orElse(null);
            return odontologo.getTurnos();
     }
}
