package com.example.demo.repositorios;

import com.example.demo.entidades.Empresa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEmpresa extends JpaRepository<Empresa, Long> {

  Optional<Empresa> findByCnpj(String cnpj);

}
