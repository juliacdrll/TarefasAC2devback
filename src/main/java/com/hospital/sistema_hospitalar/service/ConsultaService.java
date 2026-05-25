package com.hospital.sistema_hospitalar.service;

import com.hospital.sistema_hospitalar.model.Consulta;
import com.hospital.sistema_hospitalar.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> listarTodos() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Consulta atualizar(Long id, Consulta consultaAtualizada) {
        return consultaRepository.findById(id).map(consulta -> {
            consulta.setDataHora(consultaAtualizada.getDataHora());
            consulta.setMotivo(consultaAtualizada.getMotivo());
            consulta.setValor(consultaAtualizada.getValor());
            consulta.setPaciente(consultaAtualizada.getPaciente());
            consulta.setMedico(consultaAtualizada.getMedico());
            consulta.setConvenio(consultaAtualizada.getConvenio());
            consulta.setReceita(consultaAtualizada.getReceita());
            return consultaRepository.save(consulta);
        }).orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));
    }

    public void excluir(Long id) {
        consultaRepository.deleteById(id);
    }
}