package com.example.demo.services;

import com.example.demo.entities.Medico;
import com.example.demo.entities.Paciente;
import com.example.demo.mappers.PacienteMapper;
import com.example.demo.mappers.PersonaMapper;
import com.example.demo.repositories.IPacienteRepository;
import com.example.demo.repositories.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    IPacienteRepository repository;

    @Autowired
    PacienteMapper mapper;

    public List<Paciente> getPacientes(){
        return repository.findAll();
        //return discoMapper.ListDiscosToDiscosResponse(discoRepository.findAll());
    }

    public Optional<Paciente> getById(Long id){
        return (Optional<Paciente>) repository.findById(id);
    }

    public ResponseEntity savePaciente(Paciente paciente){
        repository.save(paciente);
        return ResponseEntity.ok("Registro guardado");
    }
}
