package com.example.demo.repositorios;

import com.example.demo.entidades.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioConsultas extends JpaRepository<Consulta, Long> {
}
