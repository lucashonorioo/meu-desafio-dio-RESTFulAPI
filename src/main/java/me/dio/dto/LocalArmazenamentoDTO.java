package me.dio.dto;

import me.dio.domain.model.LocalArmazenamento;

public class LocalArmazenamentoDTO {

    private Long id;
    private String tipo;
    private Long geladeiraId;

    public LocalArmazenamentoDTO() {
    }

    public LocalArmazenamentoDTO(LocalArmazenamento localArmazenamento) {
        this.id = localArmazenamento.getId();
        this.tipo = localArmazenamento.getTipo();
        if (localArmazenamento.getGeladeira() != null) {
            this.geladeiraId = localArmazenamento.getGeladeira().getId();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getGeladeiraId() {
        return geladeiraId;
    }

    public void setGeladeiraId(Long geladeiraId) {
        this.geladeiraId = geladeiraId;
    }
}