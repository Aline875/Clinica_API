package com.example.demo.entidades;

import com.google.gson.Gson;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "veterinarios")
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veterinario", nullable = true)
    private Long idVeterinario;

    @NotBlank(message = "O nome não pode estar nulo ou em branco")
    @Column(name = "nome", columnDefinition = "VARCHAR(80)", nullable = false)
    private String nome;

    @NotBlank(message = "O CRMV não pode estar nulo ou em branco")
    @Column(name = "crmv", columnDefinition = "VARCHAR(20)", nullable = false)
    private String crmv;

    @NotBlank(message = "A localização não pode estar nula ou em branco")
    @Column(name = "localizacao", columnDefinition = "VARCHAR(255)", nullable = false)
    private String localizacao;

    @NotBlank(message = "A especialidade não pode estar nula ou em branco")
    @Column(name = "especialidade", columnDefinition = "VARCHAR(255)", nullable = false)
    private String especialidade;

    @NotNull(message = "A data de nascimento não pode estar nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @OneToMany(mappedBy = "veterinarioResponsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> petsAtendidos = new ArrayList<>();

    // Construtores, getters e setters

    public Long getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Long idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
