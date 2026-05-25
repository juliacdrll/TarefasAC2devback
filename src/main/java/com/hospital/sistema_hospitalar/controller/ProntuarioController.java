package com.hospital.sistema_hospitalar.controller;

import com.hospital.sistema_hospitalar.model.Prontuario;
import com.hospital.sistema_hospitalar.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping
    public List<Prontuario> listar() {
        return prontuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> buscar(@PathVariable Long id) {
        return prontuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prontuario criar(@RequestBody Prontuario prontuario) {
        return prontuarioService.salvar(prontuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prontuario> atualizar(@PathVariable Long id, @RequestBody Prontuario prontuario) {
        try {
            return ResponseEntity.ok(prontuarioService.atualizar(id, prontuario));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        prontuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}