package com.example.demo.dto;

import java.util.ArrayList;

import com.example.demo.entidades.Consulta;

public class ConsultaResponseDTO extends BasicDTO {
    private Consulta consulta;

    public ConsultaResponseDTO() {
        super.setMensagem(new ArrayList<>());
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
