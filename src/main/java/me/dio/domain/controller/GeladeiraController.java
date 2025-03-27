package me.dio.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.dio.domain.model.Alimento;
import me.dio.domain.model.Geladeira;
import me.dio.domain.service.GeladeiraService;
import me.dio.dto.GeladeiraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/geladeira")
public class GeladeiraController {

    @Autowired
    private GeladeiraService geladeiraService;

    @GetMapping
    @Operation(summary = "Lista todas as geladeiras", description = "Retorna uma lista com todas as geladeiras cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de geladeiras retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<List<GeladeiraDTO>> findAll() {
        List<Geladeira> geladeiras = geladeiraService.findAll();
        List<GeladeiraDTO> geladeiraDTOs = geladeiras.stream()
                .map(GeladeiraDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(geladeiraDTOs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma geladeira por ID", description = "Retorna a geladeira correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Geladeira encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Geladeira não encontrada."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<GeladeiraDTO> findById(@PathVariable Long id) {
        Geladeira geladeira = geladeiraService.findById(id);
        if (geladeira == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GeladeiraDTO(geladeira));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova geladeira", description = "Cria uma nova geladeira com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Geladeira criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<Geladeira> create(@RequestBody Geladeira geladeira) {
        if (geladeira.getNome() == null || geladeira.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Geladeira geladeiraCriada = geladeiraService.create(geladeira);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(geladeiraCriada.getId())
                .toUri();
        return ResponseEntity.created(location).body(geladeiraCriada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma geladeira existente", description = "Atualiza a geladeira com o ID fornecido com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Geladeira atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Geladeira não encontrada."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<Geladeira> update(@PathVariable Long id, @RequestBody Geladeira geladeira) {
        if (geladeira.getNome() == null || geladeira.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        var geladeiraUpdate = geladeiraService.update(id, geladeira);
        return ResponseEntity.ok(geladeiraUpdate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma geladeira", description = "Remove a geladeira com o ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Geladeira removida com sucesso."),
            @ApiResponse(responseCode = "404", description = "Geladeira não encontrada."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        geladeiraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}