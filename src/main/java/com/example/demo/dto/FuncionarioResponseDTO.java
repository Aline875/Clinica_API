package com.example.demo.dto;

import com.example.demo.entidades.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioResponseDTO extends BasicDTO {

    private Funcionario funcionario;

    public FuncionarioResponseDTO() {
        super.setMensagem(new ArrayList<>());
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
