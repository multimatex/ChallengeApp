package com.rapidticket.platform.infrastructure.adapters.out.dbrepositories;

import com.rapidticket.platform.infrastructure.adapters.out.dbentities.SpectatorEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SpectatorDBRepository extends ReactiveCrudRepository<SpectatorEntity, Long> {

    Mono<SpectatorEntity> findByDni(String dni);

}