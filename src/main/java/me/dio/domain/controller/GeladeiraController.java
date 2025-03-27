package me.dio.domain.controller;

import me.dio.domain.model.Alimento;
import me.dio.domain.model.Geladeira;
import me.dio.domain.service.GeladeiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/geladeira")
public class GeladeiraController {

    @Autowired
    private GeladeiraService geladeiraService;

    @GetMapping
    public ResponseEntity<List<Geladeira>> findAll(){
        var geladeira = geladeiraService.findAll();
        return ResponseEntity.ok(geladeira);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Geladeira> findById(@PathVariable Long id){
        var geladeira = geladeiraService.findById(id);
        return ResponseEntity.ok(geladeira);
    }

    @PostMapping
    public ResponseEntity<Geladeira> create(@RequestBody Geladeira geladeira){
        Geladeira geladeiraCriada = geladeiraService.create(geladeira);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(geladeiraCriada.getId())
                .toUri();
        return ResponseEntity.created(location).body(geladeiraCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Geladeira> update(@PathVariable Long id, @RequestBody Geladeira geladeira){
        var geladeiraUpdate = geladeiraService.update(id, geladeira);
        return ResponseEntity.ok(geladeiraUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        geladeiraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
