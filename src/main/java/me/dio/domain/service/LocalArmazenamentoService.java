package me.dio.domain.service;

import me.dio.domain.model.Alimento;
import me.dio.domain.model.LocalArmazenamento;

import java.util.List;

public interface LocalArmazenamentoService extends CrudService<Long, LocalArmazenamento>{

    List<Alimento> listaAlimentosPorLocal(Long localId);
    void removerAlimentoDoLocal(Long localId, Long alimentoId);
}
