package com.hospital.sistema_hospitalar.service;

import com.hospital.sistema_hospitalar.model.Convenio;
import com.hospital.sistema_hospitalar.repository.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository convenioRepository;

    public List<Convenio> listarTodos() {
        return convenioRepository.findAll();
    }

    public Optional<Convenio> buscarPorId(Long id) {
        return convenioRepository.findById(id);
    }

    public Convenio salvar(Convenio convenio) {
        return convenioRepository.save(convenio);
    }

    public Convenio atualizar(Long id, Convenio convenioAtualizado) {
        return convenioRepository.findById(id).map(convenio -> {
            convenio.setNome(convenioAtualizado.getNome());
            convenio.setCnpj(convenioAtualizado.getCnpj());
            return convenioRepository.save(convenio);
        }).orElseThrow(() -> new RuntimeException("Convênio não encontrado!"));
    }

    public void excluir(Long id) {
        convenioRepository.deleteById(id);
    }
}