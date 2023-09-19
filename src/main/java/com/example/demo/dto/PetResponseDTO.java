package com.example.demo.dto;

import java.util.ArrayList;

import com.example.demo.entidades.Pet;

public class PetResponseDTO extends BasicDTO{
	public Pet Pet;
	public PetResponseDTO() {
		super.setMensagem(new ArrayList<>());
	}
}
