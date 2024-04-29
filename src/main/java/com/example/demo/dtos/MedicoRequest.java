package com.example.demo.dtos;

import com.example.demo.entities.Persona;
import lombok.Data;

@Data
public class MedicoRequest {
    private PersonaRequest persona;
    private String especializacion;
    private String tituto;

}
