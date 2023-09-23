package com.example.demo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.ConsultaDTO;
import com.example.demo.dto.ListaConsultaResponseDTO;
import com.example.demo.servicos.ConsultaServico;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ControladorConsulta {

    private final ConsultaServico consultaServico;

    @Autowired
    public ControladorConsulta(ConsultaServico consultaServico) {
        this.consultaServico = consultaServico;
    }

    @GetMapping("/listar")
    public ResponseEntity<ListaConsultaResponseDTO> listarConsultas() {
        ListaConsultaResponseDTO listaConsultaResponseDTO = consultaServico.listarConsultas();
        return new ResponseEntity<>(listaConsultaResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{consultaId}")
    public ResponseEntity<ConsultaDTO> getConsultaPorId(@PathVariable Long consultaId) {
        ConsultaDTO consultaDTO = consultaServico.obterConsultaPorId(consultaId);
        if (consultaDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(consultaDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> criarConsulta(@Valid @RequestBody ConsultaDTO consultaDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return criarResponseComErro(consultaDTO, bindingResult);
        }

        try {
            ConsultaDTO consultaCriada = consultaServico.criarConsulta(consultaDTO);
            return criarResponseComSucesso(consultaCriada, "Consulta cadastrada com sucesso");
        } catch (Exception e) {
            return criarResponseComErro(consultaDTO, e.getMessage());
        }
    }

    @PutMapping("/{consultaId}")
    public ResponseEntity<?> atualizarConsulta(@PathVariable Long consultaId, @Valid @RequestBody ConsultaDTO consultaDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return criarResponseComErro(consultaDTO, bindingResult);
        }

        try {
            ConsultaDTO consultaAtualizada = consultaServico.atualizarConsulta(consultaId, consultaDTO);
            return criarResponseComSucesso(consultaAtualizada, "Consulta atualizada com sucesso");
        } catch (Exception e) {
            return criarResponseComErro(consultaDTO, e.getMessage());
        }
    }

    @DeleteMapping("/{consultaId}")
    public ResponseEntity<?> excluirConsulta(@PathVariable Long consultaId) {
        boolean sucesso = consultaServico.excluirConsulta(consultaId);
        if (sucesso) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<?> criarResponseComErro(ConsultaDTO consultaDTO, BindingResult bindingResult) {
        List<String> mensagensDeErro = new ArrayList<>();
        for (ObjectError obj : bindingResult.getAllErrors()) {
            mensagensDeErro.add(obj.getDefaultMessage());
        }
        consultaDTO.setMensagem(mensagensDeErro);
        consultaDTO.setStatusCode("199");
        return new ResponseEntity<>(consultaDTO, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> criarResponseComErro(ConsultaDTO consultaDTO, String mensagemErro) {
        consultaDTO.setMensagem(Collections.singletonList(mensagemErro));
        consultaDTO.setStatusCode("199");
        return new ResponseEntity<>(consultaDTO, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> criarResponseComSucesso(ConsultaDTO consultaDTO, String mensagemSucesso) {
        consultaDTO.setMensagem(Collections.singletonList(mensagemSucesso));
        consultaDTO.setStatusCode("200");
        return new ResponseEntity<>(consultaDTO, HttpStatus.OK);
    }
}
