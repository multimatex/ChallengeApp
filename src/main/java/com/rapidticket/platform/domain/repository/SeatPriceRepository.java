package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.SeatPrice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SeatPriceRepository {
    Mono<SeatPrice> save(SeatPrice seatPrice);
    Mono<SeatPrice> findById(long id);
    Flux<SeatPrice> findAll();
    Mono<Void> deleteById(long id);
    Mono<SeatPrice> findByShowIdAndSectionId(long showId, long sectionId);
}
