package com.example.demo.dtos;

import lombok.Data;

@Data
public class PacienteResponse {
    private PersonaRequest persona;
    private String numeroFicha;

}
