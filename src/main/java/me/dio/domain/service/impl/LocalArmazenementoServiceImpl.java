package me.dio.domain.service.impl;

import jakarta.transaction.Transactional;
import me.dio.domain.model.Alimento;
import me.dio.domain.model.LocalArmazenamento;
import me.dio.domain.repository.LocalArmazenamentoRepository;
import me.dio.domain.service.AlimentoService;
import me.dio.domain.service.LocalArmazenamentoService;
import me.dio.domain.service.exception.BusinessException;
import me.dio.domain.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalArmazenementoServiceImpl implements LocalArmazenamentoService {

    @Autowired
    private LocalArmazenamentoRepository localArmazenamentoRepository;
    @Autowired
    private AlimentoService alimentoService;


    @Override
    public List<LocalArmazenamento> findAll() {
        return localArmazenamentoRepository.findAll();
    }

    @Override
    public LocalArmazenamento findById(Long id) {
        return localArmazenamentoRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public LocalArmazenamento create(LocalArmazenamento localArmazenamento) {
        if(localArmazenamento.getTipo() == null || localArmazenamento.getTipo().trim().isEmpty()){
            throw new BusinessException("O local de armazenamento é obrigatorio");
        }
        return localArmazenamentoRepository.save(localArmazenamento);
    }

    @Override
    public LocalArmazenamento update(Long id, LocalArmazenamento localArmazenamentoAtualizado) {
        LocalArmazenamento localExistente = findById(id);
        if(localArmazenamentoAtualizado.getTipo() != null || !localArmazenamentoAtualizado.getTipo().trim().isEmpty()){
            localExistente.setTipo(localArmazenamentoAtualizado.getTipo());
        }
        return localArmazenamentoRepository.save(localExistente);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        localArmazenamentoRepository.deleteById(id);
    }

    @Transactional
    public List<Alimento> listaAlimentosPorLocal(Long localId){
        LocalArmazenamento local = findById(localId);
        return local.getAlimentos();
    }

    @Transactional
    public void removerAlimentoDoLocal(Long localId, Long alimentoId){
        LocalArmazenamento local = findById(localId);
        Alimento alimentoParaRemover = alimentoService.findById(alimentoId);

        if(alimentoParaRemover != null){
            if(local.removerAlimento(alimentoParaRemover)){
                localArmazenamentoRepository.save(local);
            }
            else{
                throw new NotFoundException();
            }
        }
        else{
            throw new NotFoundException();
        }
    }
}
