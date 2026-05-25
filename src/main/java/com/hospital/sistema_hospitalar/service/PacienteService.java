package com.hospital.sistema_hospitalar.service;

import com.hospital.sistema_hospitalar.model.Paciente;
import com.hospital.sistema_hospitalar.model.Prontuario;
import com.hospital.sistema_hospitalar.model.dto.PacienteDTO;
import com.hospital.sistema_hospitalar.repository.PacienteRepository;
import com.hospital.sistema_hospitalar.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    // Métodos Auxiliares de Conversão (Mapeamento manual)
    private PacienteDTO converterParaDTO(Paciente paciente) {
        Long prontuarioId = (paciente.getProntuario() != null) ? paciente.getProntuario().getId() : null;
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getTelefone(),
                prontuarioId
        );
    }

    // GET - Listar todos devolvendo DTOs [cite: 16]
    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // GET/{id} - Buscar por ID [cite: 17]
    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
        return converterParaDTO(paciente);
    }

    // POST - Inserir recebendo DTO [cite: 18]
    public PacienteDTO salvar(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setTelefone(dto.getTelefone());

        // Vincula o prontuário se o ID foi enviado
        if (dto.getProntuarioId() != null) {
            Prontuario prontuario = prontuarioRepository.findById(dto.getProntuarioId())
                    .orElseThrow(() -> new RuntimeException("Prontuário não encontrado!"));
            paciente.setProntuario(prontuario);
        }

        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        return converterParaDTO(pacienteSalvo);
    }

    // PUT/{id} - Atualizar [cite: 19]
    public PacienteDTO atualizar(Long id, PacienteDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));

        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setTelefone(dto.getTelefone());

        if (dto.getProntuarioId() != null) {
            Prontuario prontuario = prontuarioRepository.findById(dto.getProntuarioId())
                    .orElseThrow(() -> new RuntimeException("Prontuário não encontrado!"));
            paciente.setProntuario(prontuario);
        }

        Paciente pacienteAtualizado = pacienteRepository.save(paciente);
        return converterParaDTO(pacienteAtualizado);
    }

    // DELETE/{id} - Excluir
    public void excluir(Long id) {
        pacienteRepository.deleteById(id);
    }
}