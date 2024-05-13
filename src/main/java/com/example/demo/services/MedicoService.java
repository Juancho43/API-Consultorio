package com.example.demo.services;


import com.example.demo.dtos.MedicoRequest;
import com.example.demo.dtos.MedicoResponse;
import com.example.demo.dtos.MedicosResponse;
import com.example.demo.entities.Medico;
import com.example.demo.mappers.MedicoMapper;
import com.example.demo.repositories.IMedicoRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Data
@Service
public class MedicoService {
    @Autowired
    IMedicoRepository repository;
    @Autowired
    MedicoMapper mapper;


    @Transactional
    public ResponseEntity<MedicosResponse>getMedicos(){
        try{
            return ResponseEntity.ok().body( mapper.ListToResponse(repository.findAll()));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    public ResponseEntity<MedicoResponse> getById(Long id){
        try {
            return ResponseEntity.ok(mapper.ModelToResponse(getMedicoById(id)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public Medico getMedicoById(Long id){
        return repository.findById(id).get();
    }

    @Transactional
    public ResponseEntity saveNewMedico(MedicoRequest medico){
        try {
            repository.save(mapper.RequestToNewModel(medico));
            return ResponseEntity.ok("Registro guardado");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error al guardar el registro: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<MedicoResponse> updateById(MedicoRequest request, Long id) {
        try {
            Medico medico = repository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Medico no encontrado con id: " + id));
            medico.setTitulo(request.getTitulo());
            medico.setEspecializacion(request.getEspecializacion());
            //medico.setPersona(personaMapper.RequestToModel(request.getPersona()));
            return ResponseEntity.ok(mapper.ModelToResponse(medico));
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
                return ResponseEntity.ok("Medico eliminado");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }

}
