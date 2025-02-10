package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.Performance;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PerformanceRepository {
    Mono<Performance> save(Performance performance);
    Mono<Performance> findById(long id);
    Flux<Performance> findAll();
    Mono<Void> deleteById(long id);
    Flux<Performance> findByShowId(long showId);
}
