package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketRepository {
    Mono<Ticket> save(Ticket ticket);
    Mono<Ticket> findById(long id);
    Flux<Ticket> findAll();
    Mono<Void> deleteById(long id);
    Flux<Ticket> findByReservationId(long reservationId);
    Flux<Ticket> findByShowSectionSeatId(long showSectionSeatId);
    Flux<Ticket> findByPerformanceIdAndShowSectionSeatId(long performanceId, long showSectionSeatId);
}
