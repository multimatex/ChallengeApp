package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.Spectator;
import com.rapidticket.platform.domain.repository.SpectatorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SpectatorUseCase {
    private final SpectatorRepository spectatorRepository;

    public SpectatorUseCase(SpectatorRepository spectatorRepository) {
        this.spectatorRepository = spectatorRepository;
    }

    public Mono<Spectator> create(Spectator spectator) {
        return spectatorRepository.save(spectator);
    }

    public Mono<Spectator> update(Spectator spectator) {
        return spectatorRepository.save(spectator);
    }

    public Mono<Spectator> findById(long id) {
        return spectatorRepository.findById(id);
    }

    public Flux<Spectator> findAll() {
        return spectatorRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return spectatorRepository.deleteById(id);
    }

    public Mono<Spectator> findByDni(String dni) {
        return spectatorRepository.findByDni(dni);
    }
}
