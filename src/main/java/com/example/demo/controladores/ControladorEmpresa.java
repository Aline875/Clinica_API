package com.example.demo.controladores;

import com.example.demo.dto.EmpresaDTO;
import com.example.demo.servicos.EmpresaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class ControladorEmpresa {
    @Autowired
    private EmpresaServico empresaServico;

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(empresaServico.listarEmpresas());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody EmpresaDTO dados, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(obterMensagensErro(bindingResult));
        }

        try {
            EmpresaDTO empresaCriada = empresaServico.criarEmpresa(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaCriada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getEmpresa/{id}")
    public ResponseEntity<?> getEmpresa(@PathVariable Long id) {
        EmpresaDTO empresaDTO = empresaServico.obterEmpresaPorId(id);
        if (empresaDTO == null || empresaDTO.getEmpresa() == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(empresaDTO);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody EmpresaDTO dados, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(obterMensagensErro(bindingResult));
        }

        try {
            EmpresaDTO empresaAtualizada = empresaServico.atualizarEmpresa(id, dados);
            if (empresaAtualizada == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(empresaAtualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean sucesso = empresaServico.excluirEmpresa(id);
        if (sucesso) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String obterMensagensErro(BindingResult bindingResult) {
        StringBuilder mensagens = new StringBuilder();
        for (ObjectError obj : bindingResult.getAllErrors()) {
            mensagens.append(obj.getDefaultMessage()).append("\n");
        }
        return mensagens.toString();
    }
}
