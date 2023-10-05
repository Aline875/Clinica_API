package com.example.demo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entidades.Consulta;
import com.example.demo.repositorios.RepositorioConsultas;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final RepositorioConsultas repositorioConsultas;

    @Autowired
    public ConsultaService(RepositorioConsultas repositorioConsultas) {
        this.repositorioConsultas = repositorioConsultas;
    }

    public List<Consulta> listarConsultas() {
        return repositorioConsultas.findAll();
    }

    public Consulta cadastrarConsulta(Consulta consulta) {
        return repositorioConsultas.save(consulta);
    }

    public Optional<Consulta> getConsultaPorId(Long id) {
        return repositorioConsultas.findById(id);
    }

    public Consulta atualizarConsulta(Consulta consulta) {
        return repositorioConsultas.save(consulta);
    }

    public void excluirConsulta(Long id) {
        repositorioConsultas.deleteById(id);
    }
}
