package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Consulta;

public class ListaConsultaResponseDTO extends BasicDTO {
    private int quantidadeTotal;
    private List<Consulta> consultas;

    public ListaConsultaResponseDTO() {
        super.setMensagem(new ArrayList<>());
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
