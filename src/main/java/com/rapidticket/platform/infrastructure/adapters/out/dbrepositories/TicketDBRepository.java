package com.rapidticket.platform.infrastructure.adapters.out.dbrepositories;

import com.rapidticket.platform.infrastructure.adapters.out.dbentities.TicketEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface TicketDBRepository extends ReactiveCrudRepository<TicketEntity, Long> {

    Flux<TicketEntity> findByReservationId(Long reservationId);
    Flux<TicketEntity> findByShowSectionSeatId(long showSectionSeatId);
    Flux<TicketEntity> findByPerformanceIdAndShowSectionSeatId(long performanceId, long showSectionSeatId);

}
