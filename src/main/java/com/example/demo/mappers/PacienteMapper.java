package com.example.demo.mappers;

import com.example.demo.dtos.PacienteRequest;
import com.example.demo.dtos.PacienteResponse;
import com.example.demo.dtos.PacientesResponse;
import com.example.demo.entities.Paciente;
import com.example.demo.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteMapper {
    @Autowired
    PersonaService personaService;
    public Paciente RequestToModel(PacienteRequest request){
        Paciente paciente = new Paciente();
        paciente.setId(request.getId());
        paciente.setNumeroFicha(request.getNumeroFicha());
        paciente.setPersona(personaService.getPersonaById(request.getPersonaId()));
        return paciente;
    }

    public PacientesResponse ListToResponse(List<Paciente> pacientes){
        PacientesResponse response = new PacientesResponse();
        List<PacienteResponse> responseList = new ArrayList<PacienteResponse>();
        for (Paciente model: pacientes) {
            responseList.add(ModelToResponse(model));
        }
        response.setPacientes(responseList);
        return response;
    }

    public PacienteResponse ModelToResponse(Paciente model){
        PacienteResponse response = new PacienteResponse();
        response.setId(model.getId());
        response.setIdpersona(model.getPersona().getId());
        response.setNombre(model.getPersona().getNombre());
        response.setNumeroFicha(model.getNumeroFicha());
        return response;
    }

    public PacienteRequest ModelToRequest(Paciente model){
        PacienteRequest request = new PacienteRequest();
        request.setId(model.getId());
        request.setNumeroFicha(model.getNumeroFicha());
        //request.setPersonaId();
        //response.setPersona(personaService.getMapper().ModelToRequest(model.getPersona()));
        return request;
    }
}

