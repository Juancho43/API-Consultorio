package com.example.demo.dtos;

import lombok.Data;

@Data
public class MedicoResponse {
    private PersonaRequest persona;
    private String especializacion;
    private String tituto;

}
