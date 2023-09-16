package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.ProprietarioPet;

public class ListaProprietarioPetResponseDTO extends BasicDTO{
	public int quantidadeTotal;
	public List<ProprietarioPet> proprietarioPets;
  public List<ProprietarioPet> proprietariopet;
  public List<ProprietarioPet> proprietariospet;
	public ListaProprietarioPetResponseDTO() {
		super.setMensagem(new ArrayList<>());
	}
}
