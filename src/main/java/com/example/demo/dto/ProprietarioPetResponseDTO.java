package com.example.demo.dto;

import java.util.ArrayList;

import com.example.demo.entidades.ProprietarioPet;

public class ProprietarioPetResponseDTO extends BasicDTO{
	public ProprietarioPet proprietarioPet;
	public ProprietarioPetResponseDTO() {
		super.setMensagem(new ArrayList<>());
	}
}
