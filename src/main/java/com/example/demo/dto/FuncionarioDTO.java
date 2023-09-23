package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.entidades.Funcionario;

import jakarta.validation.Valid;

public class FuncionarioDTO extends BasicDTO {
    private Long idFuncionario;
    private String nome;
    private String cpf;
    private String cargo;
    private Date dataNascimento;
    private String telefone;
    private String endereco;
    public @Valid Funcionario funcionario;

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

    public static FuncionarioDTO fromFuncionario(Funcionario funcionario) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setIdFuncionario(funcionario.getIdFuncionario());
        dto.setNome(funcionario.getNome());
        dto.setCpf(funcionario.getCpf());
        dto.setCargo(funcionario.getCargo());
        dto.setDataNascimento(funcionario.getDataNascimento());
        dto.setTelefone(funcionario.getTelefone());
        dto.setEndereco(funcionario.getEndereco());
        return dto;
    }

    public static List<FuncionarioDTO> fromFuncionarios(List<Funcionario> funcionarios) {
        List<FuncionarioDTO> dtos = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            dtos.add(fromFuncionario(funcionario));
        }
        return dtos;
    }
}
