package com.example.demo.servicos;

import com.example.demo.dto.ConsultaDTO;
import com.example.demo.dto.ListaConsultaResponseDTO;
import com.example.demo.entidades.Consultas;
import com.example.demo.repositorios.RepositorioConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaServico {

    private final RepositorioConsulta repositorioConsulta;

    @Autowired
    public ConsultaServico(RepositorioConsulta repositorioConsulta) {
        this.repositorioConsulta = repositorioConsulta;
    }

    public ConsultaDTO obterConsultaPorId(Long consultaId) {
        Optional<Consultas> consultaOptional = repositorioConsulta.findById(consultaId);
        if (consultaOptional.isPresent()) {
            Consultas consulta = consultaOptional.get();
            return mapearConsultaParaDTO(consulta);
        }
        return null;
    }

    public ConsultaDTO criarConsulta(ConsultaDTO consultaDTO) {
        Consultas novaConsulta = mapearDTOParaConsulta(consultaDTO);
        novaConsulta = repositorioConsulta.save(novaConsulta);
        return mapearConsultaParaDTO(novaConsulta);
    }

    public ListaConsultaResponseDTO listarConsultas() {
        List<Consultas> todasAsConsultas = (List<Consultas>) repositorioConsulta.findAll();
        List<ConsultaDTO> consultaDTOs = todasAsConsultas.stream()
                .map(this::mapearConsultaParaDTO)
                .collect(Collectors.toList());

        ListaConsultaResponseDTO responseDTO = new ListaConsultaResponseDTO();
        responseDTO.setConsultas(consultaDTOs);
        return responseDTO;
    }

    public boolean excluirConsulta(Long consultaId) {
        if (repositorioConsulta.existsById(consultaId)) {
            repositorioConsulta.deleteById(consultaId);
            return true;
        }
        return false;
    }

    public ConsultaDTO atualizarConsulta(Long consultaId, @Valid ConsultaDTO consultaDTO) {
        Optional<Consultas> consultaOptional = repositorioConsulta.findById(consultaId);
        if (consultaOptional.isPresent()) {
            Consultas consultaExistente = consultaOptional.get();
            atualizarDadosConsultaExistente(consultaExistente, consultaDTO);
            consultaExistente = repositorioConsulta.save(consultaExistente);
            return mapearConsultaParaDTO(consultaExistente);
        }
        return null;
    }

    private Consultas mapearDTOParaConsulta(ConsultaDTO consultaDTO) {
        Consultas consulta = new Consultas();
        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        consulta.setDescricao(consultaDTO.getDescricao());
        // Mapear outras propriedades
        return consulta;
    }

    private ConsultaDTO mapearConsultaParaDTO(Consultas consulta) {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(consulta.getId());
        consultaDTO.setDataConsulta(consulta.getDataConsulta());
        consultaDTO.setDescricao(consulta.getDescricao());
        // Mapear outras propriedades
        return consultaDTO;
    }

    private void atualizarDadosConsultaExistente(Consultas consultaExistente, ConsultaDTO consultaDTO) {
        consultaExistente.setDataConsulta(consultaDTO.getDataConsulta());
        consultaExistente.setDescricao(consultaDTO.getDescricao());
        // Atualizar outras propriedades conforme necessário
    }
}
