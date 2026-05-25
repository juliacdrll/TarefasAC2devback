package com.hospital.sistema_hospitalar.service;

import com.hospital.sistema_hospitalar.model.Medico;
import com.hospital.sistema_hospitalar.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public Medico salvar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico atualizar(Long id, Medico medicoAtualizado) {
        return medicoRepository.findById(id).map(medico -> {
            medico.setNome(medicoAtualizado.getNome());
            medico.setEspecialidade(medicoAtualizado.getEspecialidade());
            medico.setCrm(medicoAtualizado.getCrm());
            return medicoRepository.save(medico);
        }).orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
    }

    public void excluir(Long id) {
        medicoRepository.deleteById(id);
    }
}