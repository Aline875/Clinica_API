package com.example.demo.entidades;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Long id;

    @Column(name = "nome", columnDefinition = "VARCHAR(80)", nullable = false)
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 3, max = 80, message = "O nome deve conter entre 3 e 80 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome deve conter apenas letras")
    private String nome;

    @Column(name = "cnpj", columnDefinition = "VARCHAR(14)", nullable = false, unique = true)
    @NotBlank(message = "O CNPJ não pode estar em branco")
    @Size(min = 14, max = 14, message = "O CNPJ deve ter 14 caracteres")
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter apenas dígitos numéricos")
    private String cnpj;

    @OneToMany(mappedBy = "empresa")
    private List<Veterinario> veterinarios = new ArrayList<>();

    // Outros campos e getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
