package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.Auditorium;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuditoriumRepository {
    Mono<Auditorium> save(Auditorium auditorium);
    Mono<Auditorium> findById(long id);
    Flux<Auditorium> findAll();
    Mono<Void> deleteById(long id);
    Flux<Auditorium> findByLocationId(long locationId);
}
