package com.hospital.sistema_hospitalar.controller;

import com.hospital.sistema_hospitalar.model.Receita;
import com.hospital.sistema_hospitalar.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public List<Receita> listar() {
        return receitaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscar(@PathVariable Long id) {
        return receitaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Receita criar(@RequestBody Receita receita) {
        return receitaService.salvar(receita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizar(@PathVariable Long id, @RequestBody Receita receita) {
        try {
            return ResponseEntity.ok(receitaService.atualizar(id, receita));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}