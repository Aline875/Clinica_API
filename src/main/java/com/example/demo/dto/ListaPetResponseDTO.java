package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Pet;
import com.example.demo.entidades.ProprietarioPet;

public class ListaPetResponseDTO extends BasicDTO{
	public int quantidadeTotal;
	public List<ProprietarioPet> proprietarioPets;
  public List<ProprietarioPet> proprietariopet;
  public List<Pet> pet;
	public ListaPetResponseDTO() {
		super.setMensagem(new ArrayList<>());
	}
}
