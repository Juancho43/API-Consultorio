package com.example.demo.dtos;

import lombok.Data;

@Data
public class MedicoRequest {
    private Long id;
    private long personaId;
    private String especializacion;
    private String titulo;

}
