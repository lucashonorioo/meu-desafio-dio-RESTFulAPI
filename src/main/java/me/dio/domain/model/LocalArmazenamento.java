package me.dio.domain.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_local_armazenamento")
public class LocalArmazenamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String tipo;

    @ManyToOne
    @JoinColumn(name = "geladiera_id")
    private Geladeira geladeira;

    @OneToMany(mappedBy = "localArmazenamento")
    protected List<Alimento> alimentos;

    public LocalArmazenamento(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.alimentos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void adicionarAlimento(Alimento alimento){
        this.alimentos.add(alimento);
    }

    public boolean removerAlimento(Alimento alimento){
        return this.alimentos.remove(alimento);
    }

    public boolean contemAlimento(Alimento alimento){
        return this.alimentos.contains(alimento);
    }

}
