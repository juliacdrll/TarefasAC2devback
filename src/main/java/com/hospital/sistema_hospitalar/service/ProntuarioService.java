package com.hospital.sistema_hospitalar.service;

import com.hospital.sistema_hospitalar.model.Prontuario;
import com.hospital.sistema_hospitalar.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public List<Prontuario> listarTodos() {
        return prontuarioRepository.findAll();
    }

    public Optional<Prontuario> buscarPorId(Long id) {
        return prontuarioRepository.findById(id);
    }

    public Prontuario salvar(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    public Prontuario atualizar(Long id, Prontuario prontuarioAtualizado) {
        return prontuarioRepository.findById(id).map(prontuario -> {
            prontuario.setTipoSanguineo(prontuarioAtualizado.getTipoSanguineo());
            prontuario.setAlergia(prontuarioAtualizado.getAlergia());
            prontuario.setObservacoes(prontuarioAtualizado.getObservacoes());
            return prontuarioRepository.save(prontuario);
        }).orElseThrow(() -> new RuntimeException("Prontuário não encontrado!"));
    }

    public void excluir(Long id) {
        prontuarioRepository.deleteById(id);
    }
}