package com.example.demo.services;


import com.example.demo.dtos.CitaRequest;
import com.example.demo.dtos.CitaResponse;
import com.example.demo.dtos.CitasResponse;
import com.example.demo.entities.Cita;

import com.example.demo.mappers.CitaMapper;
import com.example.demo.mappers.MedicoMapper;
import com.example.demo.mappers.PacienteMapper;
import com.example.demo.repositories.ICitaRepository;
import jakarta.transaction.Transactional;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CitaService {
    @Autowired
    ICitaRepository repository;
    @Autowired
    CitaMapper mapper;
    @Autowired
    MedicoService medicoService;
    @Autowired
    PacienteService pacienteService;

    @Transactional
    public ResponseEntity<CitasResponse> getCitas(){
        try{
           return ResponseEntity.ok().body( mapper.ListToResponse(repository.findAll()));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    public ResponseEntity<CitaResponse> getById(Long id){
        try {
            Cita cita = repository.findById(id).get();
            return ResponseEntity.ok(mapper.ModelToResponse(cita));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    public ResponseEntity saveCita(CitaRequest request){
        try {
            repository.save(mapper.RequestToModel(request));
            return ResponseEntity.ok("Registro guardado");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error al guardar el registro: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<CitaResponse> updateById(CitaRequest request, Long id) {
        try {
            Cita cita = repository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Cita no encontrada con id: " + id));
            cita.setFecha(request.getFecha());
            cita.setDescripcion(request.getDescripcion());
            cita.setPaciente(pacienteService.getPacienteById(request.getPacienteId()));
            cita.setMedico(medicoService.getMedicoById(request.getMedicoId()));
            repository.save(cita);
            return ResponseEntity.ok(mapper.ModelToResponse(cita));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    public ResponseEntity<String> deleteById(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return ResponseEntity.ok("Cita eliminada ");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }

}
