package com.hospital.sistema_hospitalar.service;

import com.hospital.sistema_hospitalar.model.Receita;
import com.hospital.sistema_hospitalar.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public List<Receita> listarTodos() {
        return receitaRepository.findAll();
    }

    public Optional<Receita> buscarPorId(Long id) {
        return receitaRepository.findById(id);
    }

    public Receita salvar(Receita receita) {
        return receitaRepository.save(receita);
    }

    public Receita atualizar(Long id, Receita receitaAtualizada) {
        return receitaRepository.findById(id).map(receita -> {
            receita.setMedicamento(receitaAtualizada.getMedicamento());
            receita.setDosagem(receitaAtualizada.getDosagem());
            receita.setDuracaoDias(receitaAtualizada.getDuracaoDias());
            return receitaRepository.save(receita);
        }).orElseThrow(() -> new RuntimeException("Receita não encontrada!"));
    }

    public void excluir(Long id) {
        receitaRepository.deleteById(id);
    }
}