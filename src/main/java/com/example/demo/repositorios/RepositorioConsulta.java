package com.example.demo.repositorios;

import com.example.demo.entidades.Consultas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioConsulta extends JpaRepository<Consultas, Long> {
    // Outros métodos de consulta personalizados, se necessário
}
