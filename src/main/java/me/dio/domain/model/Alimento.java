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
    private LocalArmazenamento localArmazenamento;

    public Alimento(){

    }

    public Alimento(Long id, String nome, String categoria, int quantidade, LocalArmazenamento localArmazenamento) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        localArmazenamento = localArmazenamento;
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

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalArmazenamento getLocalArmazenamento() {
        return localArmazenamento;
    }

    public void setLocalArmazenamento(LocalArmazenamento localArmazenamento) {
        this.localArmazenamento = localArmazenamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

