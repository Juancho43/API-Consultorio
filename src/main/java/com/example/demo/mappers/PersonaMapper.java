package com.example.demo.mappers;

import com.example.demo.dtos.PersonaRequest;
import com.example.demo.dtos.PersonaResponse;
import com.example.demo.dtos.PersonasResponse;
import com.example.demo.entities.Persona;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaMapper {
    public Persona RequestToModel(PersonaRequest request){
        Persona persona = new Persona();
        persona.setId(request.getId());
        persona.setNombre(request.getNombre());
        persona.setFechaNacimiento(request.getFechaNacimiento());
        persona.setDni(request.getDni());
        return persona;
    }

    public PersonasResponse ListToResponse(List<Persona> personas){
        PersonasResponse response = new PersonasResponse();
        List<PersonaResponse> responseList = new ArrayList<PersonaResponse>();
        for (Persona model: personas) {
            responseList.add(ModelToResponse(model));
        }
        response.setPersonas(responseList);
        return response;
    }

    public PersonaResponse ModelToResponse(Persona model){
        PersonaResponse response = new PersonaResponse();
        response.setId(model.getId());
        response.setDni(model.getDni());
        response.setFechaNacimiento(model.getFechaNacimiento());
        response.setNombre(model.getNombre());
        return response;
    }

    public PersonaRequest ModelToRequest(Persona model){
        PersonaRequest response = new PersonaRequest();
        response.setDni(model.getDni());
        response.setFechaNacimiento(model.getFechaNacimiento());
        response.setNombre(model.getNombre());
        return response;
    }

}

