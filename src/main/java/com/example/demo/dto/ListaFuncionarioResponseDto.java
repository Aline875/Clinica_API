package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Funcionario;

public class ListaFuncionarioResponseDto extends BasicDTO {
    public int quantidadeTotal;
    public List<Funcionario> funcionarios;

    public ListaFuncionarioResponseDto() {
        super.setMensagem(new ArrayList<>());
    }
}
