package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Empresa;

public class EmpresaDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String endereco;
    private String statusCode;
    private List<String> mensagem;

    public EmpresaDTO() {
        mensagem = new ArrayList<>();
    }

    // Getters e setters para todos os atributos

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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getMensagem() {
        return mensagem;
    }

    public void setMensagem(List<String> mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isPresent() {
        return false;
    }

    public void setEmpresa(Empresa empresaAtualizada) {
    }

    public EmpresaDTO get() {
      return null;
    }

    // Outros getters e setters
}
