package com.example.demo.servicos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmpresaDTO;
import com.example.demo.entidades.Empresa;
import com.example.demo.repositorios.RepositorioEmpresa;

import jakarta.validation.Valid;

@Service
public class EmpresaServico {

    private final RepositorioEmpresa repositorioEmpresa;

    @Autowired
    public EmpresaServico(RepositorioEmpresa repositorioEmpresa) {
        this.repositorioEmpresa = repositorioEmpresa;
    }

    public EmpresaDTO obterEmpresaPorId(Long empresaId) {
        Optional<Empresa> empresaOptional = repositorioEmpresa.findById(empresaId);
        if (empresaOptional.isPresent()) {
            Empresa empresa = empresaOptional.get();
            return mapearEmpresaParaDTO(empresa);
        }
        return null;
    }

    public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO) {
        Empresa novaEmpresa = mapearDTOParaEmpresa(empresaDTO);
        novaEmpresa = repositorioEmpresa.save(novaEmpresa);
        empresaDTO.setEmpresa(novaEmpresa); // Defina a empresa no DTO
        empresaDTO.setStatusCode("200");
        empresaDTO.getMensagem().add("Empresa cadastrada com sucesso");
        return empresaDTO;
    }

    public List<EmpresaDTO> listarEmpresas() {
        List<Empresa> todasAsEmpresas = repositorioEmpresa.findAll();
        List<EmpresaDTO> empresaDTOs = todasAsEmpresas.stream()
                .map(this::mapearEmpresaParaDTO)
                .collect(Collectors.toList());

        return empresaDTOs;
    }

    public void excluirEmpresa(Long empresaId) {
        repositorioEmpresa.deleteById(empresaId);
    }

    public EmpresaDTO atualizarEmpresa(Long empresaId, @Valid EmpresaDTO empresaDTO) {
        Optional<Empresa> empresaOptional = repositorioEmpresa.findById(empresaId);
        if (empresaOptional.isPresent()) {
            Empresa empresaExistente = empresaOptional.get();
            atualizarDadosEmpresaExistente(empresaExistente, empresaDTO);
            empresaExistente = repositorioEmpresa.save(empresaExistente);
            return mapearEmpresaParaDTO(empresaExistente);
        }
        return null;
    }

    private Empresa mapearDTOParaEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setNome(empresaDTO.getNome());
        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setTelefone(empresaDTO.getTelefone());
        empresa.setEndereco(empresaDTO.getEndereco());
        // Mapear outros campos
        return empresa;
    }

    private EmpresaDTO mapearEmpresaParaDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(empresa.getId());
        empresaDTO.setNome(empresa.getNome());
        empresaDTO.setCnpj(empresa.getCnpj());
        empresaDTO.setTelefone(empresa.getTelefone());
        empresaDTO.setEndereco(empresa.getEndereco());
        // Mapear outros campos
        return empresaDTO;
    }

    private void atualizarDadosEmpresaExistente(Empresa empresaExistente, EmpresaDTO empresaDTO) {
        empresaExistente.setNome(empresaDTO.getNome());
        empresaExistente.setCnpj(empresaDTO.getCnpj());
        empresaExistente.setTelefone(empresaDTO.getTelefone());
        empresaExistente.setEndereco(empresaDTO.getEndereco());
        // Atualizar outros campos conforme necessário
    }
}
