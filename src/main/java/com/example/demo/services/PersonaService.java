package com.example.demo.services;

import com.example.demo.dtos.PersonaResponse;
import com.example.demo.dtos.PersonasResponse;
import com.example.demo.entities.Persona;
import com.example.demo.mappers.PersonaMapper;
import com.example.demo.repositories.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    IPersonaRepository repository;

    @Autowired
    PersonaMapper mapper;

    public List<Persona> getPersonas(){
        return repository.findAll();
        //return discoMapper.ListDiscosToDiscosResponse(discoRepository.findAll());
    }

    public Optional<Persona> getById(Long id){
        return (Optional<Persona>) repository.findById(id);
    }

    public ResponseEntity savePersona(Persona persona){
        repository.save(persona);
        return ResponseEntity.ok("Registro guardado");
    }
/*
    public Persona updateById(Persona request, Long id){
        Persona disco = discoRepository.findById(id).get();
        disco.setTitulo(request.getTitulo());
        disco.setFechaPublicacion(request.getFechaPublicacion());

        saveDisco(disco);
        return disco;
    }
*/

    public boolean deletePersonaById(Long id){
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
