package me.dio.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.dio.domain.model.Alimento;
import me.dio.domain.service.AlimentoService;
import me.dio.dto.AlimentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alimento")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping
    @Operation(summary = "Lista todos os alimentos", description = "Retorna uma lista com todos os alimentos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alimentos retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<List<AlimentoDTO>> findAll() {
        List<Alimento> alimentos = alimentoService.findAll();
        List<AlimentoDTO> alimentoDTOs = alimentos.stream()
                .map(AlimentoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(alimentoDTOs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um alimento por ID", description = "Retorna o alimento correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alimento encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Alimento não encontrado."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<AlimentoDTO> findById(@PathVariable Long id) {
        Alimento alimento = alimentoService.findById(id);
        if (alimento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new AlimentoDTO(alimento));
    }

    @PostMapping
    @Operation(summary = "Cria um novo alimento", description = "Cria um novo alimento com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alimento criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<AlimentoDTO> create(@RequestBody AlimentoDTO alimentoDTO) {
        Alimento alimento = new Alimento();
        alimento.setNome(alimentoDTO.getNome());
        alimento.setCategoria(alimentoDTO.getCategoria());
        alimento.setQuantidade(alimentoDTO.getQuantidade());
        Alimento alimentoCriado = alimentoService.create(alimento);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(alimentoCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(new AlimentoDTO(alimentoCriado));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um alimento existente", description = "Atualiza o alimento com o ID fornecido com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alimento atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos."),
            @ApiResponse(responseCode = "404", description = "Alimento não encontrado."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<AlimentoDTO> update(@PathVariable Long id, @RequestBody AlimentoDTO alimentoDTO) {
        Alimento alimento = new Alimento();
        alimento.setNome(alimentoDTO.getNome());
        alimento.setCategoria(alimentoDTO.getCategoria());
        alimento.setQuantidade(alimentoDTO.getQuantidade());
        Alimento alimentoAtualizado = alimentoService.update(id, alimento);
        return ResponseEntity.ok(new AlimentoDTO(alimentoAtualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um alimento", description = "Remove o alimento com o ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alimento removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Alimento não encontrado."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}