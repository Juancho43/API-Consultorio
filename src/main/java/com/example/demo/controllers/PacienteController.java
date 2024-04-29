package com.example.demo.controllers;

import com.example.demo.entities.Medico;
import com.example.demo.entities.Paciente;
import com.example.demo.services.MedicoService;
import com.example.demo.services.PacienteService;
import com.example.demo.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PersonaService servicePersona;

    @Autowired
    PacienteService service;

    @GetMapping("/{id}")
    public Optional<Paciente> getPacienteById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @GetMapping("/")
    public List<Paciente> getPersonas(){
        return service.getPacientes();
    }

    @PostMapping("/")
    public ResponseEntity savePaciente(@RequestBody Paciente paciente){
        return service.savePaciente(paciente);
    }
}
