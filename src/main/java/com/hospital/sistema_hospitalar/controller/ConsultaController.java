package com.hospital.sistema_hospitalar.controller;

import com.hospital.sistema_hospitalar.model.Consulta;
import com.hospital.sistema_hospitalar.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> listar() {
        return consultaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscar(@PathVariable Long id) {
        return consultaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Consulta criar(@RequestBody Consulta consulta) {
        return consultaService.salvar(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta consulta) {
        try {
            return ResponseEntity.ok(consultaService.atualizar(id, consulta));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consultaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}