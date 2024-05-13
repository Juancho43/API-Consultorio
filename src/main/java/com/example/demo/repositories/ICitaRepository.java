package com.example.demo.repositories;

import com.example.demo.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitaRepository extends JpaRepository<Cita,Long> {
}
