package com.example.demo.repositorios;

import com.example.demo.entidades.Veterinario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

  Optional<Veterinario> findByCrmvAndIdNot(String crmv, Long id);
    // Outros métodos de consulta personalizados, se necessário

  Optional<Veterinario> findByCrmv(String crmv);
}
