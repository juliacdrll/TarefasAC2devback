package com.hospital.sistema_hospitalar.model.dto;

public class ProntuarioDTO {
    private Long id;
    private String tipoSanguineo;
    private String alergia;
    private String observacoes;

    //construtores
    public ProntuarioDTO() {}

    public ProntuarioDTO(Long id, String tipoSanguineo, String alergia, String observacoes) {
        this.id = id;
        this.tipoSanguineo = tipoSanguineo;
        this.alergia = alergia;
        this.observacoes = observacoes;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipoSanguineo() { return tipoSanguineo; }
    public void setTipoSanguineo(String tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }
    public String getAlergia() { return alergia; }
    public void setAlergia(String alergia) { this.alergia = alergia; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}