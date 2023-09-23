package com.example.demo.servicos;

import com.example.demo.dto.EmpresaDTO;
import com.example.demo.entidades.Empresa;
import com.example.demo.repositorios.RepositorioEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return mapearEmpresaParaDTO(novaEmpresa);
    }

    public ListaEmpresaResponseDTO listarEmpresas() {
        List<Empresa> todasAsEmpresas = repositorioEmpresa.findAll();
        List<EmpresaDTO> empresaDTOs = todasAsEmpresas.stream()
                .map(this::mapearEmpresaParaDTO)
                .collect(Collectors.toList());

        ListaEmpresaResponseDTO responseDTO = new ListaEmpresaResponseDTO();
        responseDTO.setEmpresas(empresaDTOs);
        return responseDTO;
    }

    public boolean excluirEmpresa(Long empresaId) {
        if (repositorioEmpresa.existsById(empresaId)) {
            repositorioEmpresa.deleteById(empresaId);
            return true;
        }
        return false;
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
        // Mapear outros campos
        return empresa;
    }

    private EmpresaDTO mapearEmpresaParaDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(empresa.getId());
        empresaDTO.setNome(empresa.getNome());
        empresaDTO.setCnpj(empresa.getCnpj());
        // Mapear outros campos
        return empresaDTO;
    }

    private void atualizarDadosEmpresaExistente(Empresa empresaExistente, EmpresaDTO empresaDTO) {
        empresaExistente.setNome(empresaDTO.getNome());
        empresaExistente.setCnpj(empresaDTO.getCnpj());
        // Atualizar outros campos conforme necessário
    }
}
