package com.example.demo.dto;

import com.example.demo.entidades.Consultas;

import java.util.List;

public class ListaConsultaResponseDTO extends BasicDTO {
    private int quantidadeTotal;
    private List<ConsultaDTO> consultas;

    public ListaConsultaResponseDTO() {
        // Construtor padrão
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public List<ConsultaDTO> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaDTO> lista) {
        this.consultas = lista;
    }

    public int size() {
      return 0;
    }

    public boolean isEmpty() {
      return false;
    }
}
