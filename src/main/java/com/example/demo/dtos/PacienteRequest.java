package com.example.demo.dtos;

import lombok.Data;

@Data
public class PacienteRequest {
    private PersonaRequest persona;
    private String numeroFicha;

}
