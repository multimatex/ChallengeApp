package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.Reservation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationRepository {
    Mono<Reservation> save(Reservation reservation);
    Mono<Reservation> findById(long id);
    Flux<Reservation> findAll();
    Mono<Void> deleteById(long id);
    Flux<Reservation> findBySpectatorId(long spectatorId);
}
