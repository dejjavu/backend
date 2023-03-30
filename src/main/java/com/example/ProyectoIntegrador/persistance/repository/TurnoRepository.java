package com.example.ProyectoIntegrador.persistance.repository;

import com.example.ProyectoIntegrador.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TurnoRepository extends JpaRepository<Turno, Long> {
}