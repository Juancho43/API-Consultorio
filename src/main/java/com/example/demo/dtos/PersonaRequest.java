package com.example.demo.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class PersonaRequest {
    private String nombre;
    private Date fechaNacimiento;
    private String dni;
}
