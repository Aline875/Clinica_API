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
    public ResponseEntity<Object> cadastrarVeterinario(@RequestBody VeterinarioDTO veterinarioDTO) {
        // Verificar se o CRMV já está cadastrado
        Optional<Veterinario> existingVeterinario = veterinarioRepository.findByCrmv(veterinarioDTO.getCrmv());
        if (existingVeterinario.isPresent()) {
            return ResponseEntity.badRequest().body("O CRMV informado já está cadastrado.");
        }

        Veterinario veterinario = new Veterinario();
        BeanUtils.copyProperties(veterinarioDTO, veterinario);
        Veterinario veterinarioSalvo = veterinarioRepository.save(veterinario);
        return new ResponseEntity<>(veterinarioSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarVeterinario(@PathVariable Long id, @RequestBody VeterinarioDTO veterinarioDTO) {
        Optional<Veterinario> veterinarioOptional = veterinarioRepository.findById(id);
        if (veterinarioOptional.isPresent()) {
            // Verificar se o CRMV já está cadastrado em outro registro
            Optional<Veterinario> existingVeterinario = veterinarioRepository.findByCrmvAndIdNot(veterinarioDTO.getCrmv(), id);
            if (existingVeterinario.isPresent()) {
                return ResponseEntity.badRequest().body("O CRMV informado já está cadastrado em outro veterinário.");
            }

            Veterinario veterinario = veterinarioOptional.get();
            BeanUtils.copyProperties(veterinarioDTO, veterinario);
            Veterinario veterinarioAtualizado = veterinarioRepository.save(veterinario);
            return new ResponseEntity<>(veterinarioAtualizado, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletarVeterinario(@PathVariable Long id) {
        Optional<Veterinario> veterinarioOptional = veterinarioRepository.findById(id);
        if (veterinarioOptional.isPresent()) {
            veterinarioRepository.deleteById(id);
            return ResponseEntity.ok("Veterinário deletado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

    

