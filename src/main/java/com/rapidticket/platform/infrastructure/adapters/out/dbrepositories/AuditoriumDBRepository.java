package com.rapidticket.platform.infrastructure.adapters.out.dbrepositories;

import com.rapidticket.platform.infrastructure.adapters.out.dbentities.AuditoriumEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AuditoriumDBRepository extends ReactiveCrudRepository<AuditoriumEntity, Long> {

    Flux<AuditoriumEntity> findByLocationId(Long locationId);
}