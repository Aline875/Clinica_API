package com.example.demo.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ListaPetResponseDTO;
import com.example.demo.dto.PetResponseDTO;
import com.example.demo.entidades.Pet;
import com.example.demo.repositorios.RepositorioPet;

import io.swagger.annotations.Api;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pet")
@Api(value="API REST Pet")
public class ControladorPet {
	@Autowired
	private RepositorioPet repositorioPet;

	@GetMapping("/listar")
	public ResponseEntity<ListaPetResponseDTO> listar() {
		ListaPetResponseDTO response = new ListaPetResponseDTO();
		response.setStatusCode("200");
		List<Pet> lista = (List<Pet>) repositorioPet.findAll();
		response.quantidadeTotal = lista.size();
		if(lista.size() == 0) {
			response.getMensagem().add("Consulta sem Resultados");
		} else {
			response.pet = lista; 
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<PetResponseDTO> cadastrar(@Valid @RequestBody Pet dados, BindingResult bindingResult) {
		PetResponseDTO response = new PetResponseDTO();
		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				response.getMensagem().add(obj.getDefaultMessage());
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			try {
				dados = repositorioPet.save(dados);
				response.Pet = dados;
				response.getMensagem().add("Pet cadastrado com sucesso");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} catch (DataIntegrityViolationException e) {
				response.Pet = dados;
				response.getMensagem().add(e.getLocalizedMessage());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
	}

	@GetMapping("/getPet/{id}")
	public ResponseEntity<PetResponseDTO> getPet(@PathVariable Long id) {
		PetResponseDTO response = new PetResponseDTO();
		response.setStatusCode("200");
		Optional<Pet> buscarPet = repositorioPet.findById(id);
		if (buscarPet.isPresent() == false) {
			response.setStatusCode("199");
			response.getMensagem().add("Pet não encontrado");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.getMensagem().add("Pet encontrado");
			response.Pet = buscarPet.get();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<PetResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody Pet dados,
			BindingResult bindingResult) {
		PetResponseDTO response = new PetResponseDTO();
		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				response.getMensagem().add(obj.getDefaultMessage());
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			Optional<Pet> buscarPet = repositorioPet.findById(id);
			if (buscarPet.isPresent() == false) {
				response.setStatusCode("199");
				response.getMensagem().add("Pet não encontrado");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			} else {
				response.getMensagem().add("Professor atualizado");
				dados.id = buscarPet.get().id;
				response.Pet = repositorioPet.save(dados);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<PetResponseDTO> delete(@PathVariable Long id) {
		PetResponseDTO response = new PetResponseDTO();
		response.setStatusCode("200");
		Optional<Pet> buscarPet = repositorioPet.findById(id);
		if (buscarPet.isPresent() == false) {
			response.setStatusCode("199");
			response.getMensagem().add("Pet não encontrado");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.getMensagem().add("Pet removido");
			repositorioPet.deleteById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
}
