package com.example.demo.controllers;

import com.example.demo.dtos.MedicoResponse;
import com.example.demo.dtos.MedicosResponse;
import com.example.demo.entities.Medico;
import com.example.demo.entities.Persona;
import com.example.demo.services.MedicoService;
import com.example.demo.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    PersonaService servicePersona;

    @Autowired
    MedicoService service;

    @GetMapping("/{id}")
    public MedicoResponse getPersonaById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @GetMapping("/")
    public MedicosResponse getMedico(){
        return service.getMedicos();
    }

    @PostMapping("/")
    public ResponseEntity saveMedico(@RequestBody Medico medico){
        return service.saveMedico(medico);
    }
}
