package com.alertincident.troncon_routier_service.service;

import com.alertincident.troncon_routier_service.model.TronconRoutier;
import com.alertincident.troncon_routier_service.repository.TronconRoutierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TronconRoutierService {

    private final TronconRoutierRepository repository;

    public TronconRoutierService(TronconRoutierRepository repository) {
        this.repository = repository;
    }

    public TronconRoutier create(TronconRoutier troncon) {
        return repository.save(troncon);
    }

    public List<TronconRoutier> getAll() {
        return repository.findAll();
    }

    public TronconRoutier getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

