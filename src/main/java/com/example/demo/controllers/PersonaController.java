package com.example.demo.controllers;

import com.example.demo.dtos.PersonasResponse;
import com.example.demo.entities.Persona;
import com.example.demo.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService service;

    @GetMapping("/{id}")
    public Optional<Persona> getPersonaById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @GetMapping("/")
    public List<Persona> getPersonas(){
        return service.getPersonas();
    }

    @PostMapping("/")
    public ResponseEntity savePersona(@RequestBody Persona persona){
        return service.savePersona(persona);
    }
}
