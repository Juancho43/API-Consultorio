package com.example.demo.services;

import com.example.demo.dtos.MedicoResponse;
import com.example.demo.dtos.MedicosResponse;
import com.example.demo.entities.Medico;
import com.example.demo.entities.Persona;
import com.example.demo.mappers.MedicoMapper;
import com.example.demo.mappers.PersonaMapper;
import com.example.demo.repositories.IMedicoRepository;
import com.example.demo.repositories.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicoService {

    @Autowired
    IPersonaRepository repositoryPersona;

    @Autowired
    IMedicoRepository repository;

    @Autowired
    MedicoMapper mapper;

    public MedicosResponse getMedicos(){
        return mapper.ListMedicosToResponse(repository.findAll());
    }

    public MedicoResponse getById(Long id){
        MedicoResponse response = new MedicoResponse();
        if ((Optional<Medico>)repository.findById(id) != null){
            response = mapper.MedicoToResponse((Medico)repository.findById(id).get());
        }else{
            response = null;
        }
        return response;
    }

    public ResponseEntity saveMedico(Medico medico){
        repository.save(medico);
        return ResponseEntity.ok("Registro guardado");
    }
}
