package me.dio.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.dio.domain.model.Alimento;
import me.dio.domain.model.LocalArmazenamento;
import me.dio.domain.service.LocalArmazenamentoService;
import me.dio.dto.LocalArmazenamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/localarmazenamento")
public class LocalArmazenamentoController {

    @Autowired
    private LocalArmazenamentoService localArmazenamentoService;

    @GetMapping
    @Operation(summary = "Lista todos os locais de armazenamento", description = "Retorna uma lista com todos os locais de armazenamento cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de locais de armazenamento retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<List<LocalArmazenamentoDTO>> findAll() {
        List<LocalArmazenamento> locais = localArmazenamentoService.findAll();
        List<LocalArmazenamentoDTO> localDTOs = locais.stream()
                .map(LocalArmazenamentoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(localDTOs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um local de armazenamento por ID", description = "Retorna o local de armazenamento correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local de armazenamento encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Local de armazenamento não encontrado."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<LocalArmazenamentoDTO> findById(@PathVariable Long id) {
        LocalArmazenamento local = localArmazenamentoService.findById(id);
        if (local == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new LocalArmazenamentoDTO(local));
    }

    @PostMapping
    @Operation(summary = "Cria um novo local de armazenamento", description = "Cria um novo local de armazenamento com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Local de armazenamento criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<LocalArmazenamentoDTO> create(@RequestBody LocalArmazenamentoDTO localDTO) {
        LocalArmazenamento local = new LocalArmazenamento();
        local.setTipo(localDTO.getTipo());
        LocalArmazenamento localCriado = localArmazenamentoService.create(local);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(localCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(new LocalArmazenamentoDTO(localCriado));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um local de armazenamento existente", description = "Atualiza o local de armazenamento com o ID fornecido com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local de armazenamento atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Local de armazenamento não encontrado."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<LocalArmazenamentoDTO> update(@PathVariable Long id, @RequestBody LocalArmazenamentoDTO localDTO) {
        LocalArmazenamento local = new LocalArmazenamento();
        local.setTipo(localDTO.getTipo());
        LocalArmazenamento localAtualizado = localArmazenamentoService.update(id, local);
        return ResponseEntity.ok(new LocalArmazenamentoDTO(localAtualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um local de armazenamento", description = "Remove o local de armazenamento com o ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Local de armazenamento removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Local de armazenamento não encontrado."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        localArmazenamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{localId}/alimentos")
    @Operation(summary = "Lista alimentos de um local de armazenamento", description = "Retorna a lista de alimentos armazenados no local de armazenamento com o ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alimentos retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Local de armazenamento não encontrado."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<List<Alimento>> listarAlimentosPorLocal(@PathVariable Long localId) {
        List<Alimento> alimentos = localArmazenamentoService.listaAlimentosPorLocal(localId);
        return ResponseEntity.ok(alimentos);
    }

    @DeleteMapping("/{localId}/alimentos/{alimentoId}")
    @Operation(summary = "Remove um alimento de um local de armazenamento", description = "Remove o alimento com o ID fornecido do local de armazenamento com o ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alimento removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Local de armazenamento ou alimento não encontrado."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<Void> removerAlimentoDoLocal(@PathVariable Long localId, @PathVariable Long alimentoId) {
        localArmazenamentoService.removerAlimentoDoLocal(localId, alimentoId);
        return ResponseEntity.noContent().build();
    }
}