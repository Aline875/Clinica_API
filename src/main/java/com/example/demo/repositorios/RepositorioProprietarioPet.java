package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.ProprietarioPet;

@Repository
public interface RepositorioProprietarioPet extends JpaRepository<ProprietarioPet, Long> {

}
