package com.example.demo.controladores;

import com.example.demo.dto.ConsultaDTO;
import com.example.demo.dto.ListaConsultaResponseDTO;
import com.example.demo.servicos.ConsultaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ControladorConsulta {

    private final ConsultaServico consultaServico;

    @Autowired
    public ControladorConsulta(ConsultaServico consultaServico) {
        this.consultaServico = consultaServico;
    }

    @GetMapping("/{consultaId}")
    public ConsultaDTO getConsultaPorId(@PathVariable Long consultaId) {
        return consultaServico.obterConsultaPorId(consultaId);
    }

    @PostMapping
    public ConsultaDTO criarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        return consultaServico.criarConsulta(consultaDTO);
    }

    @GetMapping("/listar")
    public ListaConsultaResponseDTO listarConsultas() {
        return consultaServico.listarConsultas();
    }

    // Outros métodos para atualizar e excluir consultas, se necessário
}
