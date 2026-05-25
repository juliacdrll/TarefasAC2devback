package com.hospital.sistema_hospitalar.controller;

import com.hospital.sistema_hospitalar.model.dto.PacienteDTO;
import com.hospital.sistema_hospitalar.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // 1. LISTAR (GET)
    @GetMapping
    public List<PacienteDTO> listar() {
        // O service já retorna List<PacienteDTO>, então o Controller precisa declarar List<PacienteDTO>
        return pacienteService.listarTodos();
    }

    // 2. BUSCAR POR ID (GET/{id})
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscar(@PathVariable Long id) {
        // Como o service já retorna o PacienteDTO direto ou joga Exception, não usamos o método .map() aqui
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    // 3. CRIAR (POST)
    @PostMapping
    public ResponseEntity<PacienteDTO> criar(@RequestBody PacienteDTO dto) {
        // O service recebe PacienteDTO e retorna PacienteDTO
        return ResponseEntity.ok(pacienteService.salvar(dto));
    }

    // 4. ATUALIZAR (PUT/{id})
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> atualizar(@PathVariable Long id, @RequestBody PacienteDTO dto) {
        // Sem try-catch! O RestControllerAdvice cuida do erro se o paciente não existir
        return ResponseEntity.ok(pacienteService.atualizar(id, dto));
    }

    // 5. DELETAR (DELETE/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pacienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}