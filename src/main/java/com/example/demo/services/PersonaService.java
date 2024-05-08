package com.example.demo.services;

import ch.qos.logback.classic.Logger;
import com.example.demo.dtos.PersonaRequest;
import com.example.demo.dtos.PersonaResponse;
import com.example.demo.dtos.PersonasResponse;
import com.example.demo.entities.Persona;
import com.example.demo.mappers.PersonaMapper;
import com.example.demo.repositories.IPersonaRepository;
import jakarta.transaction.Transactional;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    IPersonaRepository repository;
    @Autowired
    PersonaMapper mapper;
    @Transactional
    public ResponseEntity<PersonasResponse> getPersonas(){
        try {
            return ResponseEntity.ok().body(mapper.ListToResponse(repository.findAll()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @Transactional
    public ResponseEntity<PersonaResponse> getById(Long id) {
        try {
            Persona persona = repository.findById(id).get();
            return ResponseEntity.ok(mapper.ModelToResponse(persona));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    public ResponseEntity savePersona(PersonaRequest persona) {
        try {
            repository.save(mapper.RequestToModel(persona));
            return ResponseEntity.ok("Registro guardado");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error al guardar el registro: " + e.getMessage());
        }
    }
    @Transactional
    public ResponseEntity<PersonaResponse> updateById(PersonaRequest request, Long id) {
        try {
            Persona persona = repository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Persona no encontrada con id: " + id));

            persona.setDni(request.getDni());
            persona.setNombre(request.getNombre());
            persona.setFechaNacimiento(request.getFechaNacimiento());
            repository.save(persona);
            return ResponseEntity.ok(mapper.ModelToResponse(persona));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @Transactional
    public ResponseEntity<String> deletePersonaById(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return ResponseEntity.ok("Persona eliminada");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }


}
