package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.Seat;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SeatRepository {
    Mono<Seat> save(Seat seat);
    Mono<Seat> findById(long id);
    Flux<Seat> findAll();
    Mono<Void> deleteById(long id);
    Flux<Seat> findByAuditoriumId(long auditoriumId);
}
