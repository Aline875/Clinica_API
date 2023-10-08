package com.example.demo.entidades;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.google.gson.Gson;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "proprietariopet")
public class ProprietarioPet<ConsultaProprietario> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = true)
    public Long id;

    @Min(value = 0, message = "A matricula deverá ser maior que zero")
    @Max(value = 1000000, message = "A matricula deverá ser menor que 1000000")
    @Column(name = "matricula", columnDefinition = "int", nullable = false)
    private int matricula;

    @NotBlank(message = "O nome não pode estar nulo ou em branco")
    @Length(min = 3, max = 80, message = "O Nome deve conter no máximo 80 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome só deve conter letras")
    @Column(name = "nome", columnDefinition = "VARCHAR(80)", nullable = false)
    private String nome;

    @NotBlank(message = "O CPF não pode estar nulo ou em branco")
    @Length(min = 11, max = 11, message = "O CPF deve conter 11 números")
    @Pattern(regexp = "^[0-9]+$", message = "O CPF só deve conter números")
    @Column(name = "cpf", columnDefinition = "VARCHAR(11)", unique = true, nullable = false)
    private String cpf;
    @NotBlank(message = "O RG não pode estar nulo ou em branco")
    @Length(min = 6, max = 11, message = "O RG deve conter entre 6 e 10 números")
    @Pattern(regexp = "^[0-9]+$", message = "O RG só deve conter números")
    @Column(name = "rg", columnDefinition = "VARCHAR(10)",unique = true, nullable = false)
    private String rg;
    @Column(name = "telefone", columnDefinition = "VARCHAR(15)")
    private String telefone;
    @Column(name = "email", columnDefinition = "VARCHAR(100)")
    private String email;

    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL)
    private List<Pet> pets;

    

    // Construtores, getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @OneToMany(mappedBy = "proprietario")
    private List<Consulta> consultas;

}
