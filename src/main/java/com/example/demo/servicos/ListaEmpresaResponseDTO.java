package com.example.demo.servicos;

import java.util.List;

import org.springframework.validation.ObjectError;

import com.example.demo.dto.EmpresaDTO;

public class ListaEmpresaResponseDTO {

  public Object quantidadeTotal;

  public void setEmpresas(List<EmpresaDTO> empresaDTOs) {
  }

  public void setStatusCode(String string) {
  }

  public boolean isEmpty() {
    return false;
  }

  public List<ObjectError> getMensagem() {
    return null;
  }

  public Object getEmpresas() {
    return null;
  }

}
