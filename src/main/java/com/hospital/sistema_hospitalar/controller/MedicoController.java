package com.hospital.sistema_hospitalar.controller;

import com.hospital.sistema_hospitalar.model.Medico;
import com.hospital.sistema_hospitalar.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> listar() {
        return medicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscar(@PathVariable Long id) {
        return medicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medico criar(@RequestBody Medico medico) {
        return medicoService.salvar(medico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medico) {
        try {
            return ResponseEntity.ok(medicoService.atualizar(id, medico));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}