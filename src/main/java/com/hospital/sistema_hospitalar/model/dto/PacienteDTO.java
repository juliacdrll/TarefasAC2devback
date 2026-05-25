package com.hospital.sistema_hospitalar.model.dto;

public class PacienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private Long prontuarioId;

    // Construtor padrão
    public PacienteDTO() {
    }

    // Construtor completo
    public PacienteDTO(Long id, String nome, String cpf, String telefone, Long prontuarioId) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.prontuarioId = prontuarioId;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public Long getProntuarioId() { return prontuarioId; }
    public void setProntuarioId(Long prontuarioId) { this.prontuarioId = prontuarioId; }
}