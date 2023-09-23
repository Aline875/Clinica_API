package com.example.demo.entidades;

import com.google.gson.Gson;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario", nullable = true)
    private Long idFuncionario;

    @NotBlank(message = "O nome não pode estar nulo ou em branco")
    @Size(min = 3, max = 80, message = "O Nome deve conter entre 3 e 80 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome só deve conter letras")
    @Column(name = "nome", columnDefinition = "VARCHAR(80)", nullable = false)
    private String nome;

    @NotBlank(message = "O CPF não pode estar nulo ou em branco")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 números")
    @Pattern(regexp = "^[0-9]+$", message = "O CPF só deve conter números")
    @Column(name = "cpf", columnDefinition = "VARCHAR(11)", unique = true, nullable = false)
    private String cpf;

    @NotBlank(message = "O cargo não pode estar nulo ou em branco")
    @Column(name = "cargo", columnDefinition = "VARCHAR(255)", nullable = false)
    private String cargo;

    @NotNull(message = "A data de nascimento não pode estar nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "telefone", columnDefinition = "VARCHAR(20)")
    private String telefone;

    @Column(name = "endereco", columnDefinition = "VARCHAR(255)")
    private String endereco;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Pet> pets;

    // Construtores, getters e setters

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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
