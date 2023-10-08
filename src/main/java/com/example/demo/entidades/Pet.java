package com.example.demo.entidades;

import org.hibernate.validator.constraints.Length;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long id;

    @Column(name = "nome", columnDefinition = "VARCHAR(80)", nullable = false)
    @NotBlank(message = "O nome não pode estar nulo ou em branco")
    @Length(min = 3, max = 80, message = "O Nome deve conter no máximo 80 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome só deve conter letras")
    private String nome;

    @Column(name = "raca", columnDefinition = "VARCHAR(80)", nullable = false)
    @NotBlank(message = "A raça não pode estar nula ou em branco")
    @Length(min = 3, max = 80, message = "A raça deve conter no máximo 80 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "A raça só deve conter letras")
    private String raca;

    @Column(name = "historico_do_pet", columnDefinition = "VARCHAR(255)", nullable = false)
    @Length(max = 255, message = "O histórico do pet deve conter no máximo 255 caracteres")
    private String historicoDoPet;

    @Column(name = "sexo", columnDefinition = "VARCHAR(10)", nullable = false)
    @NotBlank(message = "O sexo não pode estar nulo ou em branco")
    @Pattern(regexp = "^(M|F)$", message = "O sexo deve ser 'M' para masculino ou 'F' para feminino")
    private String sexo;

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private ProprietarioPet proprietario;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    // Getters e Setters para todas as propriedades.

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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getHistoricoDoPet() {
        return historicoDoPet;
    }

    public void setHistoricoDoPet(String historicoDoPet) {
        this.historicoDoPet = historicoDoPet;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    // Outros getters e setters

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
