package com.example.demo.dto;

import com.example.demo.entidades.Consultas;

import java.util.List;

public class ListaConsultaResponseDTO extends BasicDTO {
    private int quantidadeTotal;
    private List<Consultas> consultas;

    public ListaConsultaResponseDTO() {
        // Construtor padrão
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public List<Consultas> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consultas> consultas) {
        this.consultas = consultas;
    }
}
