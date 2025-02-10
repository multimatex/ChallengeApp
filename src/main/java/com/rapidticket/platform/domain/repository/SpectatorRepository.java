package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.Spectator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SpectatorRepository {
    Mono<Spectator> save(Spectator spectator);
    Mono<Spectator> findById(long id);
    Flux<Spectator> findAll();
    Mono<Void> deleteById(long id);
    Mono<Spectator> findByDni(String dni);
}
