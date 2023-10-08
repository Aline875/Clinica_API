package com.example.demo.entidades;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 80, message = "O Nome deve conter no máximo 80 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome só deve conter letras")
    @Column(name = "nome", columnDefinition = "VARCHAR(80)", nullable = false)
    private String nome;

    @NotBlank(message = "O CPF não pode estar em branco")
    @Column(name = "cpf", columnDefinition = "VARCHAR(11)", unique = true, nullable = false)
    private String cpf;

    @NotBlank(message = "O cargo não pode estar em branco")
    @Size(max = 100, message = "O cargo deve conter no máximo 100 caracteres")
    @Column(name = "cargo", columnDefinition = "VARCHAR(100)", nullable = false)
    private String cargo;

    @NotBlank(message = "O telefone não pode estar em branco")
    @Size(max = 15, message = "O telefone deve conter no máximo 15 caracteres")
    @Column(name = "telefone", columnDefinition = "VARCHAR(15)", nullable = false)
    private String telefone;

    @NotBlank(message = "O endereço não pode estar em branco")
    @Size(max = 200, message = "O endereço deve conter no máximo 200 caracteres")
    @Column(name = "endereco", columnDefinition = "VARCHAR(200)", nullable = false)
    private String endereco;

    // Construtores, getters e setters

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
