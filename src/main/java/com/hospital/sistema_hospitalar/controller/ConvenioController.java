package com.hospital.sistema_hospitalar.controller;

import com.hospital.sistema_hospitalar.model.Convenio;
import com.hospital.sistema_hospitalar.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/convenios")
public class ConvenioController {

    @Autowired
    private ConvenioService convenioService;

    @GetMapping
    public List<Convenio> listar() {
        return convenioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Convenio> buscar(@PathVariable Long id) {
        return convenioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Convenio criar(@RequestBody Convenio convenio) {
        return convenioService.salvar(convenio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Convenio> atualizar(@PathVariable Long id, @RequestBody Convenio convenio) {
        try {
            return ResponseEntity.ok(convenioService.atualizar(id, convenio));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        convenioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}