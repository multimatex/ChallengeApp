package com.rapidticket.platform.infrastructure.adapters.out.dbrepositories;

import com.rapidticket.platform.domain.model.ShowSectionSeat;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.ShowSectionSeatEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ShowSectionSeatDBRepository extends ReactiveCrudRepository<ShowSectionSeatEntity, Long> {

    Flux<ShowSectionSeatEntity> findByShowId(Long showId);
    Flux<ShowSectionSeatEntity> findBySectionId(Long sectionId);
    Flux<ShowSectionSeatEntity> findByShowIdAndSectionId(long showId,long sectionId);
    Flux<ShowSectionSeat> findByPricesId(long priceId);

}