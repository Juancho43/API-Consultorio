package com.example.demo.controllers;

import com.example.demo.dtos.MedicoRequest;
import com.example.demo.dtos.MedicoResponse;
import com.example.demo.dtos.MedicosResponse;
import com.example.demo.entities.Medico;
import com.example.demo.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    @Autowired
    MedicoService service;
    @GetMapping("/")
    public ResponseEntity<MedicosResponse> getMedico(){
        return service.getMedicos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> getMedicoById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @PostMapping("/")
    public ResponseEntity saveMedico(@RequestBody MedicoRequest medico){
        return service.saveNewMedico(medico);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> updateById(@PathVariable("id") Long id, @RequestBody MedicoRequest request ){
        return service.updateById(request, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        return service.deleteById(id);
    }
}
