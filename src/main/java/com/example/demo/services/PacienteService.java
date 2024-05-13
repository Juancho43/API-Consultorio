package com.example.demo.services;


import com.example.demo.dtos.PacienteRequest;
import com.example.demo.dtos.PacienteResponse;
import com.example.demo.dtos.PacientesResponse;
import com.example.demo.entities.Paciente;

import com.example.demo.mappers.PacienteMapper;
import com.example.demo.mappers.PersonaMapper;
import com.example.demo.repositories.IPacienteRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Data
public class PacienteService {
    @Autowired
    IPacienteRepository repository;
    @Autowired
    PacienteMapper mapper;

    @Transactional
    public ResponseEntity<PacientesResponse> getPacientes(){
        try {
            return ResponseEntity.ok().body(mapper.ListToResponse(repository.findAll()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    public ResponseEntity<PacienteResponse> getById(Long id){
        try {
            return ResponseEntity.ok(mapper.ModelToResponse(getPacienteById(id)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public Paciente getPacienteById(Long id){
        return repository.findById(id).get();
    }

    @Transactional
    public ResponseEntity savePaciente(PacienteRequest paciente){
        try {
            repository.save(mapper.RequestToModel(paciente));
            return ResponseEntity.ok("Registro guardado");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error al guardar el registro: " + e.getMessage());
        }

    }
    @Transactional
    public ResponseEntity<PacienteResponse> updateById(PacienteRequest request, Long id) {
        try {
            Paciente paciente = repository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Paciente no encontrado con id: " + id));
            paciente.setNumeroFicha(request.getNumeroFicha());
            //paciente.setPersona(personaMapper.RequestToModel(request.getPersona()));
            return ResponseEntity.ok(mapper.ModelToResponse(paciente));
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
                return ResponseEntity.ok("Paciente eliminado");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }

}
