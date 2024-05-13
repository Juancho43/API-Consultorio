package com.example.demo.dtos;

import lombok.Data;

@Data
public class PacienteResponse {
    private long id;
    private long idpersona;
    private String nombre;
    private String numeroFicha;

}
