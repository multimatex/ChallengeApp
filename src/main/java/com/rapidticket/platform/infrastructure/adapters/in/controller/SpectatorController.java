package com.rapidticket.platform.infrastructure.adapters.in.controller;

import com.rapidticket.platform.domain.model.Spectator;
import com.rapidticket.platform.application.usecase.SpectatorUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/spectators")
public class SpectatorController {

    private final SpectatorUseCase spectatorUseCase;

    public SpectatorController(SpectatorUseCase spectatorUseCase) {
        this.spectatorUseCase = spectatorUseCase;
    }

    @GetMapping
    public Flux<Spectator> getAllSpectators() {
        return spectatorUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Spectator> getSpectatorById(@PathVariable long id) {
        return spectatorUseCase.findById(id);
    }

    @PostMapping
    public Mono<Spectator> createSpectator(@RequestBody Spectator spectator) {
        return spectatorUseCase.create(spectator);
    }

    @PutMapping("/{id}")
    public Mono<Spectator> updateSpectator(@PathVariable long id, @RequestBody Spectator spectator) {
        spectator.setId(id);
        return spectatorUseCase.update(spectator);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteSpectator(@PathVariable long id) {
        return spectatorUseCase.deleteById(id);
    }

    @GetMapping("/dni/{dni}")
    public Mono<Spectator> findByDni(@PathVariable String dni) {
        return spectatorUseCase.findByDni(dni);
    }
}
