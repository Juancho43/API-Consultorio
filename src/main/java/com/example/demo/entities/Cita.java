package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Citas")
@Data
public class Cita{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private Date fecha;
    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
    private String descripcion;
}
