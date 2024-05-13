package com.example.demo.dtos;
import lombok.Data;

import java.util.Date;
@Data
public class CitaResponse {
    private long id;
    private Date fecha;
    private MedicoResponse medico;
    private PacienteResponse paciente;
    private String descripcion;
}
