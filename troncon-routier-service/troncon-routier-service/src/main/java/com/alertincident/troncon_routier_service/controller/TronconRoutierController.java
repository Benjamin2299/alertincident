package com.alertincident.troncon_routier_service.controller;

import com.alertincident.troncon_routier_service.model.TronconRoutier;
import com.alertincident.troncon_routier_service.service.TronconRoutierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/troncons")
public class TronconRoutierController {

    private final TronconRoutierService service;

    public TronconRoutierController(TronconRoutierService service) {
        this.service = service;
    }

    @PostMapping
    public TronconRoutier create(@RequestBody TronconRoutier troncon) {
        return service.create(troncon);
    }

    @GetMapping
    public List<TronconRoutier> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TronconRoutier getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

