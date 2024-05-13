package com.example.demo.dtos;
import lombok.Data;

import java.util.Date;
@Data
public class CitaRequest {
    private long id;
    private Date fecha;
    private long medicoId;
    private long pacienteId;
    private String descripcion;
}
