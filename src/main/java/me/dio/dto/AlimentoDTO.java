package me.dio.dto;
import me.dio.domain.model.Alimento;

public class AlimentoDTO {

    private Long id;
    private String nome;
    private String categoria;
    private int quantidade;
    private Long localArmazenamentoId;

    public AlimentoDTO() {
    }

    public AlimentoDTO(Alimento alimento) {
        this.id = alimento.getId();
        this.nome = alimento.getNome();
        this.categoria = alimento.getCategoria();
        this.quantidade = alimento.getQuantidade();
        if (alimento.localArmazenamento() != null) {
            this.localArmazenamentoId = alimento.localArmazenamento().getId();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Long getLocalArmazenamentoId() {
        return localArmazenamentoId;
    }

    public void setLocalArmazenamentoId(Long localArmazenamentoId) {
        this.localArmazenamentoId = localArmazenamentoId;
    }
}