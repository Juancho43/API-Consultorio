package com.example.demo.mappers;

import com.example.demo.dtos.MedicoRequest;
import com.example.demo.dtos.MedicoResponse;
import com.example.demo.dtos.MedicosResponse;
import com.example.demo.entities.Medico;
import com.example.demo.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicoMapper {
    @Autowired
    PersonaService personaService;
    public MedicosResponse ListToResponse(List<Medico> medicoList){
        MedicosResponse response = new MedicosResponse();
        List<MedicoResponse> responseList = new ArrayList<>();
        for (Medico medico: medicoList) {
            responseList.add(ModelToResponse(medico));
        }
        response.setMedicos(responseList);
        return response;
    }
    public MedicoResponse ModelToResponse(Medico medico){
        MedicoResponse response = new MedicoResponse();
        response.setId(medico.getId());
        response.setNombre(medico.getPersona().getNombre());
        response.setTitulo(medico.getTitulo());
        response.setEspecializacion(medico.getEspecializacion());
        response.setIdpersona(medico.getPersona().getId());
        response.setNombre(medico.getPersona().getNombre());
        return response;
    }
    public Medico RequestToNewModel(MedicoRequest request){
        Medico medico = new Medico();
        medico.setTitulo(request.getTitulo());
        medico.setEspecializacion(request.getEspecializacion());
        medico.setPersona(personaService.getPersonaById(request.getPersonaId()));
        return medico;
    }
    public MedicoRequest ModelToRequest(Medico model){
        MedicoRequest request = new MedicoRequest();
        request.setId(model.getId());
        request.setEspecializacion(model.getEspecializacion());
        request.setTitulo(model.getTitulo());
        request.setPersonaId(model.getPersona().getId());
        return request;
    }

   public Medico ResponseToModel(MedicoResponse response){
       Medico medico = new Medico();
       medico.setId(response.getId());
       medico.setTitulo(response.getTitulo());
       medico.setEspecializacion(response.getEspecializacion());
       return medico;
   }


}
