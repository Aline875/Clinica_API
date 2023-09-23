package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Funcionario;

@Repository
public interface RepositorioFuncionario extends JpaRepository<Funcionario, Long> {

}
