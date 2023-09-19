package com.example.demo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositorios.RepositorioProprietarioPet;

@RestController
@RequestMapping("controladorbasico")
public class ControladorBasico {
	@Autowired
	private RepositorioProprietarioPet repositorioProprietarioPet;
	
	@GetMapping("/getAll")
	public String getAll() {
		return "Primeira API - Chamou o método getAll";
	}

	@GetMapping("/get/{id}")
	public String get(@PathVariable Long id) {
		return "Primeira API - Chamou o método get, com o ID: " + id;
	}

	@PutMapping("/put/{id}")
	public String put(@PathVariable Long id) {
		return "Primeira API - Chamou o método put, com o ID: " + id;
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		return "Primeira API - Chamou o método delete, com o ID: " + id;
	}

	@PostMapping("/post")
	public String post() {
		return "Primeira API - Chamou o método post";
	}
}
