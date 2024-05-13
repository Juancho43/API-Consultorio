package com.example.demo.controllers;


import com.example.demo.dtos.PacienteRequest;
import com.example.demo.dtos.PacienteResponse;
import com.example.demo.dtos.PacientesResponse;
import com.example.demo.entities.Paciente;
import com.example.demo.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPacienteById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @GetMapping("/")
    public ResponseEntity<PacientesResponse> getPacientes(){
        return service.getPacientes();
    }
    @PostMapping("/")
    public ResponseEntity savePaciente(@RequestBody PacienteRequest paciente){
        return service.savePaciente(paciente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> updateById(@PathVariable("id") Long id, @RequestBody PacienteRequest request ){
        return service.updateById(request, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        return service.deleteById(id);
    }
}
