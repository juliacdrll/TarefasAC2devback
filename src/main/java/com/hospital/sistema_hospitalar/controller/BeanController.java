package com.hospital.sistema_hospitalar.controller;

import com.hospital.sistema_hospitalar.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bean") // Rota pedida na atividade 
public class BeanController {

    @Autowired
    @Qualifier("consultaCompleteBean") // Força o Spring a buscar o Bean com o nome exato do método
    private Consulta consultaCompleteBean;

    @GetMapping
    public Consulta obterObjetoCompleto() {
        return consultaCompleteBean;
    }
}