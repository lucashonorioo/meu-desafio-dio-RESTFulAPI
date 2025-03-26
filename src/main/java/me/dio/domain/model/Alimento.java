package me.dio.domain.model;

import jakarta.persistence.*;

@Entity(name = "tb_alimento")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "local_armazenamento_id")
    private LocalArmazenamento LocalArmazenamentoId;

    public Alimento(Long id, String nome, String categoria, int quantidade, LocalArmazenamento localArmazenamentoId) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        LocalArmazenamentoId = localArmazenamentoId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalArmazenamento getLocalArmazenamentoId() {
        return LocalArmazenamentoId;
    }

}

