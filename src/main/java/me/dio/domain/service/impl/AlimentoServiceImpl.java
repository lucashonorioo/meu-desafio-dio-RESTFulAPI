package me.dio.domain.service.impl;

import me.dio.domain.model.Alimento;
import me.dio.domain.model.LocalArmazenamento;
import me.dio.domain.repository.AlimentoRepository;
import me.dio.domain.repository.LocalArmazenamentoRepository;
import me.dio.domain.service.AlimentoService;
import me.dio.domain.service.exception.BusinessException;
import me.dio.domain.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlimentoServiceImpl implements AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private LocalArmazenamentoRepository localArmazenamentoRepository;


    @Override
    public List<Alimento> findAll() {
        return alimentoRepository.findAll();
    }

    @Override
    public Alimento findById(Long id) {
       return alimentoRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Alimento create(Alimento alimento) {
        if(alimento.getNome() == null || alimento.getNome().trim().isEmpty()){
            throw new BusinessException("O nome do alimento é obrigatorio");
        }
        if(alimento.getCategoria() == null || alimento.getCategoria().trim().isEmpty()){
            throw new BusinessException("A categoria do alimento é obrigatoria");
        }
        if(alimento.getQuantidade() <= 0){
            throw new BusinessException("A quantidade de alimento dever maior que zero");
        }
        if(alimento.getLocalArmazenamentoId() == null || alimento.getLocalArmazenamentoId().getId() == null){
            throw new BusinessException("O ID do local de armazenamento precisa ser valido");
        }
        Long localArmazenamentoId = alimento.getLocalArmazenamentoId().getId();
        Optional<LocalArmazenamento> localArmazenamentoOptional = localArmazenamentoRepository.findById(localArmazenamentoId);

        if(localArmazenamentoOptional.isPresent()){
            alimento.setLocalArmazenamentoId(localArmazenamentoOptional.get());
            return alimentoRepository.save(alimento);
        }
        else{
            throw new NotFoundException();
        }
    }

    @Override
    public Alimento update(Long id, Alimento alimentoAtualizado) {
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(id);
        if(alimentoOptional.isPresent()){
            Alimento alimentoExistente = alimentoOptional.get();
            if(alimentoAtualizado.getNome() != null && !alimentoAtualizado.getNome().trim().isEmpty()){
                alimentoExistente.setNome(alimentoAtualizado.getNome());
            }
            else{
                throw new BusinessException("O novo nome do alimento não pode ser vazio");
            }
            return alimentoRepository.save(alimentoExistente);
        }else{
            throw new NotFoundException();
        }

    }

    @Override
    public void delete(Long id) {
        if(!alimentoRepository.existsById(id)){
            throw new NotFoundException();
        }
        alimentoRepository.deleteById(id);
    }
}
