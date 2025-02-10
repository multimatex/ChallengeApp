package com.rapidticket.platform.infrastructure.adapters.out.dbrepositories;

import com.rapidticket.platform.infrastructure.adapters.out.dbentities.PerformanceEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PerformanceDBRepository extends ReactiveCrudRepository<PerformanceEntity, Long> {

        Flux<PerformanceEntity> findByShowId(Long showId);
}
