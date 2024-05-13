package com.example.demo.dtos;

import lombok.Data;

@Data
public class PacienteRequest {
    private long id;
    private String numeroFicha;
    private long personaId;
}
