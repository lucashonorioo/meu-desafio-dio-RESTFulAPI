package me.dio.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_geladeira")
public class Geladeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;


    @OneToMany(mappedBy = "geladeira", fetch = FetchType.EAGER)
    @JsonIgnore
    public List<LocalArmazenamento> locais;


    public Geladeira() {
        this.locais = new ArrayList<>();
    }

    public Geladeira(String nome) {
        this.nome = nome;
        this.locais = new ArrayList<>();
    }

    public List<LocalArmazenamento> getLocais() {
        return locais;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



}