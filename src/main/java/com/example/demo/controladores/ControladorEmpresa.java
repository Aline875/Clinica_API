package com.example.demo.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.EmpresaDTO;
import com.example.demo.entidades.Empresa;
import com.example.demo.repositorios.RepositorioEmpresa;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class ControladorEmpresa {

    @Autowired
    private RepositorioEmpresa repositorioEmpresa;

    @GetMapping("/listar")
    public ResponseEntity<List<EmpresaDTO>> listarEmpresas() {
        List<Empresa> empresas = repositorioEmpresa.findAll();
        List<EmpresaDTO> empresaDTOs = empresas.stream()
                .map(this::mapearEmpresaParaDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(empresaDTOs, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<EmpresaDTO> cadastrarEmpresa(@Valid @RequestBody EmpresaDTO dados, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return criarRespostaDeErro(HttpStatus.BAD_REQUEST, extrairMensagensDeErro(bindingResult));
        }

        try {
            // Verificar se o CNPJ já está cadastrado
            Optional<Empresa> existingEmpresa = repositorioEmpresa.findByCnpj(dados.getCnpj());
            if (existingEmpresa.isPresent()) {
                return criarRespostaDeErro(HttpStatus.BAD_REQUEST, List.of("O CNPJ informado já está cadastrado."));
            }

            Empresa novaEmpresa = mapearDTOParaEmpresa(dados);
            novaEmpresa = repositorioEmpresa.save(novaEmpresa);

            return criarRespostaDeSucesso(mapearEmpresaParaDTO(novaEmpresa), HttpStatus.CREATED, "Empresa cadastrada com sucesso");
        } catch (DataIntegrityViolationException e) {
            return criarRespostaDeErro(HttpStatus.BAD_REQUEST, List.of(e.getLocalizedMessage()));
        }
    }

    @GetMapping("/getEmpresa/{id}")
    public ResponseEntity<EmpresaDTO> getEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = repositorioEmpresa.findById(id);

        if (empresaOptional.isPresent()) {
            return criarRespostaDeSucesso(mapearEmpresaParaDTO(empresaOptional.get()), HttpStatus.OK, "Empresa encontrada");
        } else {
            return criarRespostaDeErro(HttpStatus.NOT_FOUND, List.of("Empresa não encontrada"));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EmpresaDTO> atualizarEmpresa(@PathVariable Long id, @Valid @RequestBody EmpresaDTO dados, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return criarRespostaDeErro(HttpStatus.BAD_REQUEST, extrairMensagensDeErro(bindingResult));
        }

        try {
            Optional<Empresa> empresaOptional = repositorioEmpresa.findById(id);

            if (empresaOptional.isPresent()) {
                Empresa empresaExistente = empresaOptional.get();
                atualizarDadosEmpresaExistente(empresaExistente, dados);
                empresaExistente = repositorioEmpresa.save(empresaExistente);

                return criarRespostaDeSucesso(mapearEmpresaParaDTO(empresaExistente), HttpStatus.OK, "Empresa atualizada com sucesso");
            } else {
                return criarRespostaDeErro(HttpStatus.NOT_FOUND, List.of("Empresa não encontrada"));
            }
        } catch (DataIntegrityViolationException e) {
            return criarRespostaDeErro(HttpStatus.BAD_REQUEST, List.of(e.getLocalizedMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmpresaDTO> excluirEmpresa(@PathVariable Long id) {
        try {
            Optional<Empresa> empresaOptional = repositorioEmpresa.findById(id);

            if (empresaOptional.isPresent()) {
                repositorioEmpresa.deleteById(id);
                return criarRespostaDeSucesso(mapearEmpresaParaDTO(empresaOptional.get()), HttpStatus.OK, "Empresa excluída com sucesso");
            } else {
                return criarRespostaDeErro(HttpStatus.NOT_FOUND, List.of("Empresa não encontrada"));
            }
        } catch (Exception e) {
            return criarRespostaDeErro(HttpStatus.BAD_REQUEST, List.of("Erro ao excluir empresa"));
        }
    }

    private ResponseEntity<EmpresaDTO> criarRespostaDeErro(HttpStatus status, List<String> mensagens) {
        EmpresaDTO response = new EmpresaDTO();
        response.setStatusCode(status.toString());
        response.getMensagem().addAll(mensagens);
        return new ResponseEntity<>(response, status);
    }

    private ResponseEntity<EmpresaDTO> criarRespostaDeSucesso(EmpresaDTO empresaDTO, HttpStatus status, String mensagem) {
        empresaDTO.setStatusCode(status.toString());
        empresaDTO.getMensagem().add(mensagem);
        return new ResponseEntity<>(empresaDTO, status);
    }

    private List<String> extrairMensagensDeErro(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

    private Empresa mapearDTOParaEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setNome(empresaDTO.getNome());
        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setTelefone(empresaDTO.getTelefone());
        empresa.setEndereco(empresaDTO.getEndereco());
        return empresa;
    }

    private EmpresaDTO mapearEmpresaParaDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(empresa.getId());
        empresaDTO.setNome(empresa.getNome());
        empresaDTO.setCnpj(empresa.getCnpj());
        empresaDTO.setTelefone(empresa.getTelefone());
        empresaDTO.setEndereco(empresa.getEndereco());
        return empresaDTO;
    }

    private void atualizarDadosEmpresaExistente(Empresa empresaExistente, EmpresaDTO empresaDTO) {
        empresaExistente.setNome(empresaDTO.getNome());
        empresaExistente.setCnpj(empresaDTO.getCnpj());
        empresaExistente.setTelefone(empresaDTO.getTelefone());
        empresaExistente.setEndereco(empresaDTO.getEndereco());
    }
}
