package me.dio.domain.controller;

import me.dio.domain.model.Alimento;
import me.dio.domain.model.LocalArmazenamento;
import me.dio.domain.service.LocalArmazenamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/localarmazenamento")
public class LocalArmazenamentoController {

    @Autowired
    private LocalArmazenamentoService localArmazenamentoService;

    @GetMapping
    public ResponseEntity<List<LocalArmazenamento>> findAll(){
        var local = localArmazenamentoService.findAll();
        return ResponseEntity.ok(local);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalArmazenamento> findById(@PathVariable Long id){
        var local = localArmazenamentoService.findById(id);
        return ResponseEntity.ok(local);
    }

    @PostMapping
    public ResponseEntity<LocalArmazenamento> create(@RequestBody LocalArmazenamento localArmazenamento){
        var localCriado = localArmazenamentoService.create(localArmazenamento);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(localCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(localCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalArmazenamento> update(@PathVariable Long id, @RequestBody LocalArmazenamento localArmazenamento){
        var localUpdate = localArmazenamentoService.update(id, localArmazenamento);
        return ResponseEntity.ok(localUpdate);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        localArmazenamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{localId}/alimentos")
    public ResponseEntity<List<Alimento>> listarAlimentosPorLocal(@PathVariable Long localId){
        List<Alimento> alimentos = localArmazenamentoService.listaAlimentosPorLocal(localId);
        return ResponseEntity.ok(alimentos);
    }

    @DeleteMapping("/{localId}/alimentos/{alimentoId}")
    public ResponseEntity<Void> removerAlimentoDoLocal(@PathVariable Long localId, @PathVariable Long alimentoId){
        localArmazenamentoService.removerAlimentoDoLocal(localId,alimentoId);
        return ResponseEntity.noContent().build();
    }


}
