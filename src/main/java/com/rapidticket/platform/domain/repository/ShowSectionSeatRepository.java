package com.rapidticket.platform.domain.repository;

import com.rapidticket.platform.domain.model.ShowSectionSeat;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShowSectionSeatRepository {
    Mono<ShowSectionSeat> save(ShowSectionSeat showSectionSeat);
    Mono<ShowSectionSeat> findById(long id);
    Flux<ShowSectionSeat> findAll();
    Mono<Void> deleteById(long id);
    Flux<ShowSectionSeat> findByShowId(long showId);
    Flux<ShowSectionSeat> findBySectionId(long sectionId);
    Flux<ShowSectionSeat> findByShowIdAndSectionId(long showId,long sectionId);
    Flux<ShowSectionSeat> findByPricesId(long priceId);
}
