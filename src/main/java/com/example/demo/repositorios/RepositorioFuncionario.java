package com.example.demo.repositorios;

import com.example.demo.entidades.Funcionario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioFuncionario extends JpaRepository<Funcionario, Long> {

  Optional<Funcionario> findByCpf(String cpf);

}
