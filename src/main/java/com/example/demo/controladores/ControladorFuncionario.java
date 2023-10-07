package com.example.demo.controladores;

import com.example.demo.dto.FuncionarioResponseDTO;
import com.example.demo.entidades.Funcionario;
import com.example.demo.repositorios.RepositorioFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class ControladorFuncionario {

    @Autowired
    private RepositorioFuncionario repositorioFuncionario;

    @PostMapping("/cadastrar")
    public ResponseEntity<FuncionarioResponseDTO> cadastrarFuncionario(@Valid @RequestBody Funcionario dados, BindingResult bindingResult) {
        FuncionarioResponseDTO response = new FuncionarioResponseDTO();
        response.setStatusCode("200");

        if (bindingResult.hasErrors()) {
            response.setStatusCode("199");
            for (ObjectError obj : bindingResult.getAllErrors()) {
                response.getMensagem().add(obj.getDefaultMessage());
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            try {
                // Verificar se o CPF já está cadastrado
                Optional<Funcionario> existingFuncionario = repositorioFuncionario.findByCpf(dados.getCpf());
                if (existingFuncionario.isPresent()) {
                    response.setStatusCode("199");
                    response.getMensagem().add("O CPF informado já está cadastrado.");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

                dados = repositorioFuncionario.save(dados);
                response.setFuncionario(dados);
                response.getMensagem().add("Funcionário cadastrado com sucesso");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                response.setFuncionario(dados);
                response.getMensagem().add(e.getLocalizedMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/getFuncionario/{id}")
    public ResponseEntity<FuncionarioResponseDTO> getFuncionario(@PathVariable Long id) {
        FuncionarioResponseDTO response = new FuncionarioResponseDTO();
        response.setStatusCode("200");
        try {
            Optional<Funcionario> buscarFuncionario = repositorioFuncionario.findById(id);
            if (buscarFuncionario.isPresent()) {
                response.getMensagem().add("Funcionário encontrado");
                response.setFuncionario(buscarFuncionario.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatusCode("199");
                response.getMensagem().add("Funcionário não encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatusCode("199");
            response.getMensagem().add("Erro ao buscar funcionário");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody Funcionario dados, BindingResult bindingResult) {
        FuncionarioResponseDTO response = new FuncionarioResponseDTO();
        response.setStatusCode("200");

        if (bindingResult.hasErrors()) {
            response.setStatusCode("199");
            for (ObjectError obj : bindingResult.getAllErrors()) {
                response.getMensagem().add(obj.getDefaultMessage());
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            try {
                Optional<Funcionario> funcionarioExistente = repositorioFuncionario.findById(id);
                if (funcionarioExistente.isPresent()) {
                    // Atualize os campos relevantes do funcionário existente com os novos dados
                    Funcionario funcionarioAtualizado = funcionarioExistente.get();
                    funcionarioAtualizado.setNome(dados.getNome());
                    funcionarioAtualizado.setCpf(dados.getCpf());
                    funcionarioAtualizado.setCargo(dados.getCargo());
                    funcionarioAtualizado.setTelefone(dados.getTelefone());
                    funcionarioAtualizado.setEndereco(dados.getEndereco());

                    // Salve o funcionário atualizado no banco de dados
                    funcionarioAtualizado = repositorioFuncionario.save(funcionarioAtualizado);

                    response.setFuncionario(funcionarioAtualizado);
                    response.getMensagem().add("Funcionário atualizado com sucesso");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.setStatusCode("199");
                    response.getMensagem().add("Funcionário não encontrado");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
            } catch (DataIntegrityViolationException e) {
                response.setFuncionario(dados);
                response.getMensagem().add(e.getLocalizedMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FuncionarioResponseDTO> excluirFuncionario(@PathVariable Long id) {
        FuncionarioResponseDTO response = new FuncionarioResponseDTO();
        response.setStatusCode("200");

        try {
            Optional<Funcionario> funcionarioExistente = repositorioFuncionario.findById(id);
            if (funcionarioExistente.isPresent()) {
                // Exclua o funcionário do banco de dados
                repositorioFuncionario.deleteById(id);

                response.setFuncionario(funcionarioExistente.get());
                response.getMensagem().add("Funcionário excluído com sucesso");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatusCode("199");
                response.getMensagem().add("Funcionário não encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatusCode("199");
            response.getMensagem().add("Erro ao excluir funcionário");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = repositorioFuncionario.findAll();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    // ... (outros métodos)

}
