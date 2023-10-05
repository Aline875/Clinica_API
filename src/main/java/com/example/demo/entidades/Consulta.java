package com.example.demo.entidades;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotNull(message = "A data da consulta é obrigatória")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_consulta", nullable = false)
    private Date dataConsulta;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    // Getters, setters e construtores

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

    public List<Consulta> listarConsultas() {
      return null;
    }

    public Consulta cadastrarConsulta(@Valid Consulta dados) {
      return null;
    }

    public Optional<Consulta> getConsultaPorId(Long id2) {
      return null;
    }

    public Consulta atualizarConsulta(@Valid Consulta dados) {
      return null;
    }

    public void excluirConsulta(Long id2) {
    }

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private ProprietarioPet proprietario;

 
}
