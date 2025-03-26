package me.dio.domain.service.impl;

import me.dio.domain.model.Geladeira;
import me.dio.domain.repository.GeladeiraRepository;
import me.dio.domain.service.GeladeiraService;
import me.dio.domain.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeladeiraServiceImpl implements GeladeiraService {

    @Autowired
    private GeladeiraRepository geladeiraRepository;

    @Override
    public List<Geladeira> findAll() {
        return geladeiraRepository.findAll();
    }

    @Override
    public Geladeira findById(Long id) {
        return geladeiraRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public Geladeira create(Geladeira geladeira) {
        return geladeiraRepository.save(geladeira);
    }

    @Override
    public Geladeira update(Long id, Geladeira geladeira) {
        Optional<Geladeira> geladeiraExistenteOptional = geladeiraRepository.findById(id);
        if (geladeiraExistenteOptional.isPresent()) {
            Geladeira geladeiraExistente = geladeiraExistenteOptional.get();
            return geladeiraRepository.save(geladeiraExistente);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void delete(Long id) {
        if(!geladeiraRepository.existsById(id)){
            throw new NotFoundException();
        }
        geladeiraRepository.deleteById(id);
    }
}
