package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Pet;

@Repository
public interface RepositorioPet extends JpaRepository<Pet, Long> {

}
