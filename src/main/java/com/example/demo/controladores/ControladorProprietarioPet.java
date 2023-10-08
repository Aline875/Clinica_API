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

import com.example.demo.dto.ListaProprietarioPetResponseDTO;
import com.example.demo.dto.ProprietarioPetResponseDTO;
import com.example.demo.entidades.ProprietarioPet;
import com.example.demo.repositorios.RepositorioProprietarioPet;

import io.swagger.annotations.Api;
import jakarta.validation.Valid;

@RestController
@RequestMapping("proprietariopet")
@Api(value = "API REST ProprietarioPet")
public class ControladorProprietarioPet {
    @Autowired
    private RepositorioProprietarioPet repositorioProprietarioPet;

    @GetMapping("/listar")
    public ResponseEntity<ListaProprietarioPetResponseDTO> listar() {
        ListaProprietarioPetResponseDTO response = new ListaProprietarioPetResponseDTO();
        response.setStatusCode("200");
        List<ProprietarioPet> lista = (List<ProprietarioPet>) repositorioProprietarioPet.findAll();
        response.quantidadeTotal = lista.size();
        if (lista.size() == 0) {
            response.getMensagem().add("Consulta sem Resultados");
        } else {
            response.proprietariopet = lista;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProprietarioPetResponseDTO> cadastrar(@Valid @RequestBody ProprietarioPet dados,
            BindingResult bindingResult) {
        ProprietarioPetResponseDTO response = new ProprietarioPetResponseDTO();
        response.setStatusCode("200");
        if (bindingResult.hasErrors()) {
            response.setStatusCode("199");
            for (ObjectError obj : bindingResult.getAllErrors()) {
                response.getMensagem().add(obj.getDefaultMessage());
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            try {
                if (repositorioProprietarioPet.findByCpf(dados.getCpf()).size() > 0) {
                    response.getMensagem().add("O CPF informado já está cadastrado");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

                if (repositorioProprietarioPet.findByRg(dados.getRg()).size() > 0) {
                    response.getMensagem().add("O RG informado já está cadastrado");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

                dados = repositorioProprietarioPet.save(dados);
                response.proprietarioPet = dados;
                response.getMensagem().add("ProprietarioPet cadastrado com sucesso");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                response.proprietarioPet = dados;
                response.getMensagem().add(e.getLocalizedMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/getProprietarioPet/{id}")
    public ResponseEntity<ProprietarioPetResponseDTO> getProprietarioPet(@PathVariable Long id) {
        ProprietarioPetResponseDTO response = new ProprietarioPetResponseDTO();
        response.setStatusCode("200");
        Optional<ProprietarioPet> buscarProprietarioPet = repositorioProprietarioPet.findById(id);
        if (buscarProprietarioPet.isPresent() == false) {
            response.setStatusCode("199");
            response.getMensagem().add("ProprietarioPet não encontrado");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.getMensagem().add("ProprietarioPet encontrado");
            response.proprietarioPet = buscarProprietarioPet.get();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProprietarioPetResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProprietarioPet dados,
            BindingResult bindingResult) {
        ProprietarioPetResponseDTO response = new ProprietarioPetResponseDTO();
        response.setStatusCode("200");
        if (bindingResult.hasErrors()) {
            response.setStatusCode("199");
            for (ObjectError obj : bindingResult.getAllErrors()) {
                response.getMensagem().add(obj.getDefaultMessage());
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            Optional<ProprietarioPet> buscarProprietarioPet = repositorioProprietarioPet.findById(id);
            if (buscarProprietarioPet.isPresent() == false) {
                response.setStatusCode("199");
                response.getMensagem().add("ProprietarioPet não encontrado");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                response.getMensagem().add("ProprietarioPet atualizado");
                dados.id = buscarProprietarioPet.get().id;
                response.proprietarioPet = repositorioProprietarioPet.save(dados);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProprietarioPetResponseDTO> delete(@PathVariable Long id) {
        ProprietarioPetResponseDTO response = new ProprietarioPetResponseDTO();
        response.setStatusCode("200");
        Optional<ProprietarioPet> buscarProprietarioPet = repositorioProprietarioPet.findById(id);
        if (buscarProprietarioPet.isPresent() == false) {
            response.setStatusCode("199");
            response.getMensagem().add("ProprietarioPet não encontrado");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.getMensagem().add("ProprietarioPet removido");
            repositorioProprietarioPet.deleteById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
