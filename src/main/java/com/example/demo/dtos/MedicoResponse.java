package com.example.demo.dtos;

import lombok.Data;

@Data
public class MedicoResponse {
    private long id;
    private long idpersona;
    private String nombre;
    private String especializacion;
    private String titulo;

}
