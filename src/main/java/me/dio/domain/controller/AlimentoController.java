package me.dio.domain.controller;

import me.dio.domain.model.Alimento;
import me.dio.domain.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alimento")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping
    public ResponseEntity<List<Alimento>> findAll(){
        var alimento = alimentoService.findAll();
        return ResponseEntity.ok(alimento);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> findById(@PathVariable Long id){
        var alimento = alimentoService.findById(id);
        return ResponseEntity.ok(alimento);

    }

    @PostMapping
    public ResponseEntity<Alimento> create(@RequestBody Alimento alimento){
        var alimentoCriado = alimentoService.create(alimento);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(alimentoCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(alimentoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alimento> update(@PathVariable Long id, @RequestBody Alimento alimento){
        var alimentoUpdate = alimentoService.update(id,alimento);
        return ResponseEntity.ok(alimentoUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        alimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
