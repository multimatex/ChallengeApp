package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.Location;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LocationRepository {
    Mono<Location> save(Location location);
    Mono<Location> findById(long id);
    Flux<Location> findAll();
    Mono<Void> deleteById(long id);
}
