package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.Section;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SectionRepository {
    Mono<Section> save(Section section);
    Mono<Section> findById(long id);
    Flux<Section> findAll();
    Mono<Void> deleteById(long id);
}
