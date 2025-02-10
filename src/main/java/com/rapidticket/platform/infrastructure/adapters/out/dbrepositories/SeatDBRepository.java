package com.rapidticket.platform.infrastructure.adapters.out.dbrepositories;

import com.rapidticket.platform.infrastructure.adapters.out.dbentities.SeatEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SeatDBRepository extends ReactiveCrudRepository<SeatEntity, Long> {

    Flux<SeatEntity> findByAuditoriumId(Long auditoriumId);

}
