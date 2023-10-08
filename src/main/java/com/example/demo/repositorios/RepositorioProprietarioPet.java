package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.ProprietarioPet;

@Repository
public interface RepositorioProprietarioPet extends JpaRepository<ProprietarioPet, Long> {
    public List<ProprietarioPet> findByCpf(String cpf);

    public List<ProprietarioPet> findByRg(String rg);
}
