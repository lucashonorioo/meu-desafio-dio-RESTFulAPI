package me.dio.domain.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_geladeira")
public class Geladeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "geladeira")
    public List<LocalArmazenamento> locais;

    public Geladeira() {
        this.locais = new ArrayList<>();
    }

    public List<LocalArmazenamento> getLocais() {
        return locais;
    }
}
