package me.dio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import me.dio.domain.model.Geladeira;

@Schema(description = "DTO para Geladeira")
public class GeladeiraDTO {

    @Schema(description = "ID da geladeira")
    private Long id;

    @Schema(description = "Nome da geladeira")
    private String nome;


    public GeladeiraDTO() {
    }

    public GeladeiraDTO(Geladeira geladeira) {
        this.id = geladeira.getId();
        this.nome = geladeira.getNome();
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
}