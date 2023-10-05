package com.example.demo.repositorios;

import com.example.demo.entidades.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioFuncionario extends JpaRepository<Funcionario, Long> {

}
