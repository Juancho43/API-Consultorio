package com.example.demo.mappers;

import com.example.demo.dtos.MedicoResponse;
import com.example.demo.dtos.MedicosResponse;
import com.example.demo.entities.Medico;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicoMapper {
    public MedicosResponse ListMedicosToResponse(List<Medico> medicoList){

        MedicosResponse mresponse = new MedicosResponse();
        List<MedicoResponse> responseList = new ArrayList<>();
        for (Medico medico: medicoList) {
            responseList.add(MedicoToResponse(medico));
        }
        mresponse.setMedicos(responseList);
        return mresponse;
    }
    public MedicoResponse MedicoToResponse(Medico medico){
        MedicoResponse response = new MedicoResponse();
        response.setNombre(medico.getPersona().getNombre());
        response.setTituto(medico.getTituto());
        response.setEspecializacion(medico.getEspecializacion());
        return response;
    }
}
