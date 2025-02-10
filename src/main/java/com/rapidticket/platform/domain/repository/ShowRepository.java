package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.Show;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShowRepository {
    Mono<Show> save(Show show);
    Mono<Show> findById(long id);
    Flux<Show> findAll();
    Mono<Void> deleteById(long id);
}
