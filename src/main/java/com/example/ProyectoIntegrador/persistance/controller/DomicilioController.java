package com.example.ProyectoIntegrador.persistance.controller;

import com.example.ProyectoIntegrador.entities.Domicilio;

import com.example.ProyectoIntegrador.persistance.service.DomicilioService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domicilio")
@RequiredArgsConstructor

public class DomicilioController {
    @Autowired
    private final DomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<Domicilio> guardarDomicilio(@RequestBody Domicilio domicilio) {

        return ResponseEntity.ok(domicilioService.guardarDomicilio(domicilio));
    }


}