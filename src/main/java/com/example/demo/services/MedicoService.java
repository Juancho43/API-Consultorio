package com.example.demo.services;

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

    public List<Medico> getMedicos(){
        return repository.findAll();
        //return discoMapper.ListDiscosToDiscosResponse(discoRepository.findAll());
    }

    public Optional<Medico> getById(Long id){
        return (Optional<Medico>) repository.findById(id);
    }

    public ResponseEntity saveMedico(Medico medico){
        repository.save(medico);
        return ResponseEntity.ok("Registro guardado");
    }
}
