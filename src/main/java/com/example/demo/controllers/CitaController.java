package com.example.demo.controllers;


import com.example.demo.dtos.CitaRequest;
import com.example.demo.dtos.CitaResponse;
import com.example.demo.dtos.CitasResponse;
import com.example.demo.entities.Cita;
import com.example.demo.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    CitaService service;
    @GetMapping("/")
    public ResponseEntity<CitasResponse> getCitas(){
        return service.getCitas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> getCitaById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @PostMapping("/")
    public ResponseEntity saveCita(@RequestBody CitaRequest cita){
        return service.saveCita(cita);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CitaResponse> updateById(@PathVariable("id") Long id, @RequestBody CitaRequest request ){
        return service.updateById(request, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        return service.deleteById(id);
    }
}
