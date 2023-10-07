package com.example.demo.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import com.example.demo.servicos.ConsultaService;
import com.example.demo.dto.ConsultaResponseDTO;
import com.example.demo.dto.ListaConsultaResponseDTO;
import com.example.demo.entidades.Consulta;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consulta")
@Api(value = "API REST Consulta")
public class ControladorConsultas {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/listar")
    public ResponseEntity<ListaConsultaResponseDTO> listar() {
        ListaConsultaResponseDTO response = new ListaConsultaResponseDTO();
        response.setStatusCode("200");
        List<Consulta> lista = consultaService.listarConsultas();
        response.setQuantidadeTotal(lista.size());
        if (lista.isEmpty()) {
            response.getMensagem().add("Consulta sem Resultados");
        } else {
            response.setConsultas(lista);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ConsultaResponseDTO> cadastrar(@Valid @RequestBody Consulta dados, BindingResult bindingResult) {
        ConsultaResponseDTO response = new ConsultaResponseDTO();
        response.setStatusCode("200");

        // Verifique se a consulta com o mesmo ID já existe
        Optional<Consulta> consultaExistente = consultaService.getConsultaPorId(dados.getId());

        if (consultaExistente.isPresent()) {
            response.setStatusCode("199");
            response.getMensagem().add("ID de consulta já cadastrado");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            response.setStatusCode("199");
            for (ObjectError obj : bindingResult.getAllErrors()) {
                response.getMensagem().add(obj.getDefaultMessage());
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            try {
                Consulta consultaCadastrada = consultaService.cadastrarConsulta(dados);
                response.setConsulta(consultaCadastrada);
                response.getMensagem().add("Consulta cadastrada com sucesso");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                response.setConsulta(dados);
                response.getMensagem().add(e.getLocalizedMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/getConsulta/{id}")
    public ResponseEntity<ConsultaResponseDTO> getConsulta(@PathVariable Long id) {
        ConsultaResponseDTO response = new ConsultaResponseDTO();
        response.setStatusCode("200");
        Optional<Consulta> consultaOptional = consultaService.getConsultaPorId(id);
        if (!consultaOptional.isPresent()) {
            response.setStatusCode("199");
            response.getMensagem().add("Consulta não encontrada");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.getMensagem().add("Consulta encontrada");
            response.setConsulta(consultaOptional.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody Consulta dados,
            BindingResult bindingResult) {
        ConsultaResponseDTO response = new ConsultaResponseDTO();
        response.setStatusCode("200");
        if (bindingResult.hasErrors()) {
            response.setStatusCode("199");
            for (ObjectError obj : bindingResult.getAllErrors()) {
                response.getMensagem().add(obj.getDefaultMessage());
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            Optional<Consulta> consultaOptional = consultaService.getConsultaPorId(id);
            if (!consultaOptional.isPresent()) {
                response.setStatusCode("199");
                response.getMensagem().add("Consulta não encontrada");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                response.getMensagem().add("Consulta atualizada");
                dados.setId(consultaOptional.get().getId());
                Consulta consultaAtualizada = consultaService.atualizarConsulta(dados);
                response.setConsulta(consultaAtualizada);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ConsultaResponseDTO> delete(@PathVariable Long id) {
        ConsultaResponseDTO response = new ConsultaResponseDTO();
        response.setStatusCode("200");
        Optional<Consulta> consultaOptional = consultaService.getConsultaPorId(id);
        if (!consultaOptional.isPresent()) {
            response.setStatusCode("199");
            response.getMensagem().add("Consulta não encontrada");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.getMensagem().add("Consulta removida");
            consultaService.excluirConsulta(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
