package com.example.demo.controllers;

import com.example.demo.dtos.PersonaRequest;
import com.example.demo.dtos.PersonaResponse;
import com.example.demo.dtos.PersonasResponse;
import com.example.demo.entities.Persona;
import com.example.demo.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService service;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponse> getPersonaById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @GetMapping("/")
    public ResponseEntity<PersonasResponse> getPersonas(){
        return service.getPersonas();
    }

    @PostMapping("/")
    public ResponseEntity<PersonaResponse> savePersona(@RequestBody PersonaRequest persona){
        return service.savePersona(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponse> updateById(@PathVariable("id") Long id, @RequestBody PersonaRequest request ){
        return service.updateById(request, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        return service.deletePersonaById(id);
    }


}
