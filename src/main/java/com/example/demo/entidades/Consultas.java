package com.example.demo.entidades;

import com.google.gson.Gson;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.context.config.ConfigData;

@Entity
@Table(name = "consultas")
public class Consultas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = true)
    private Long id;

    @NotNull(message = "A data da consulta não pode estar nula")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_consulta", nullable = false)
    private Date dataConsulta;

    @NotBlank(message = "A descrição da consulta não pode estar em branco")
    @Column(name = "descricao", columnDefinition = "VARCHAR(255)", nullable = false)
    private String descricao;


    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

 
    // Construtores, getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
