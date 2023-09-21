package com.example.demo.dto;

import java.util.Date;

public class ConsultaDTO {
    private Long id;
    private Date dataConsulta;
    private String descricao;
    private Long petId; // ID do animal de estimação associado à consulta

    public ConsultaDTO() {
        // Construtor padrão
    }

    // Getters e setters para os atributos

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

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }
}
