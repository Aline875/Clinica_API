package com.example.demo.dto;

import java.util.ArrayList;

import com.example.demo.entidades.ProprietarioPet;

import jakarta.validation.Valid;

public class ProprietarioPetResponseDTO extends BasicDTO{
	public ProprietarioPet professor;
  public @Valid ProprietarioPet proprietariopet;
  public @Valid ProprietarioPet ProprietarioPet;
	public ProprietarioPetResponseDTO() {
		super.setMensagem(new ArrayList<>());
	}
}
