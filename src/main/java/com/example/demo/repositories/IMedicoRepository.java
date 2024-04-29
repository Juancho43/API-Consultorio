package com.example.demo.repositories;

import com.example.demo.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicoRepository extends JpaRepository<Medico,Long> {
}
