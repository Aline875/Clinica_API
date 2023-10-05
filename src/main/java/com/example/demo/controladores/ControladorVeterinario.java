package com.example.demo.controladores;

import com.example.demo.dto.VeterinarioDTO;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorios.VeterinarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veterinario")
public class ControladorVeterinario {
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Veterinario>> listarVeterinarios() {
        List<Veterinario> veterinarios = veterinarioRepository.findAll();
        return new ResponseEntity<>(veterinarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> buscarVeterinarioPorId(@PathVariable Long id) {
        Optional<Veterinario> veterinarioOptional = veterinarioRepository.findById(id);
        return veterinarioOptional.map(veterinario -> new ResponseEntity<>(veterinario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Veterinario> cadastrarVeterinario(@RequestBody VeterinarioDTO veterinarioDTO) {
        Veterinario veterinario = new Veterinario();
        BeanUtils.copyProperties(veterinarioDTO, veterinario);
        Veterinario veterinarioSalvo = veterinarioRepository.save(veterinario);
        return new ResponseEntity<>(veterinarioSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Veterinario> atualizarVeterinario(@PathVariable Long id, @RequestBody VeterinarioDTO veterinarioDTO) {
        Optional<Veterinario> veterinarioOptional = veterinarioRepository.findById(id);
        if (veterinarioOptional.isPresent()) {
            Veterinario veterinario = veterinarioOptional.get();
            BeanUtils.copyProperties(veterinarioDTO, veterinario);
            Veterinario veterinarioAtualizado = veterinarioRepository.save(veterinario);
            return new ResponseEntity<>(veterinarioAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarVeterinario(@PathVariable Long id) {
        Optional<Veterinario> veterinarioOptional = veterinarioRepository.findById(id);
        if (veterinarioOptional.isPresent()) {
            veterinarioRepository.deleteById(id);
            return ResponseEntity.ok("Deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veterinário não encontrado");
        }
    }
}

    

