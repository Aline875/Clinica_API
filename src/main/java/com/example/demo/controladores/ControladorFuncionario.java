package com.example.demo.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.entidades.Funcionario;
import com.example.demo.repositorios.RepositorioFuncionario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class ControladorFuncionario {
    @Autowired
    private RepositorioFuncionario repositorioFuncionario;

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        List<Funcionario> funcionarios = repositorioFuncionario.findAll();
        if (funcionarios.isEmpty()) {
            return ResponseEntity.badRequest().body("Consulta sem Resultados");
        }
        return ResponseEntity.ok(funcionarios);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Funcionario dados, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            dados = repositorioFuncionario.save(dados);
            return ResponseEntity.ok("Funcionário cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getFuncionario/{id}")
    public ResponseEntity<?> getFuncionario(@PathVariable Long id) {
        Optional<Funcionario> buscarFuncionario = repositorioFuncionario.findById(id);
        if (buscarFuncionario.isPresent()) {
            return ResponseEntity.ok(buscarFuncionario.get());
        } else {
            return ResponseEntity.badRequest().body("Funcionário não encontrado");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario dados) {
        dados.setIdFuncionario(id);
        try {
            dados = repositorioFuncionario.save(dados);
            return ResponseEntity.ok("Funcionário atualizado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Funcionario> buscarFuncionario = repositorioFuncionario.findById(id);
        if (buscarFuncionario.isPresent()) {
            repositorioFuncionario.deleteById(id);
            return ResponseEntity.ok("Funcionário removido");
        } else {
            return ResponseEntity.badRequest().body("Funcionário não encontrado");
        }
    }
}
