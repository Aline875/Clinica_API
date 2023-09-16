package com.example.demo.dto;

import java.util.ArrayList;

public class BasicDTO {
	private String statusCode;
	private ArrayList<String> mensagem;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public ArrayList<String> getMensagem() {
		return mensagem;
	}
	public void setMensagem(ArrayList<String> mensagem) {
		this.mensagem = mensagem;
	}
}
