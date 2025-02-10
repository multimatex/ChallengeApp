package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.Performance;
import com.rapidticket.platform.domain.repository.PerformanceRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.PerformanceEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.PerformanceDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PerformanceRepositoryAdapter implements PerformanceRepository {

    private final PerformanceDBRepository performanceDBRepository;

    public PerformanceRepositoryAdapter(PerformanceDBRepository performanceDBRepository) {
        this.performanceDBRepository = performanceDBRepository;
    }

    @Override
    public Mono<Performance> save(Performance performance) {
        return performanceDBRepository.save(toEntity(performance))
                .map(this::toDomain);
    }

    @Override
    public Mono<Performance> findById(long id) {
        return performanceDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Performance> findAll() {
        return performanceDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return performanceDBRepository.deleteById(id);
    }

    @Override
    public Flux<Performance> findByShowId(long showId) {
        return performanceDBRepository.findAll()
                .filter(performanceEntity -> performanceEntity.getShowId() == showId)
                .map(this::toDomain);
    }


    private PerformanceEntity toEntity(Performance domain) {
        if (domain == null) return null;
        return new PerformanceEntity(
                domain.getId(),
                domain.getShowId(),
                domain.getAuditoriumId(),
                domain.getDateTime()
        );
    }

    private Performance toDomain(PerformanceEntity entity) {
        if (entity == null) return null;
        return new Performance(
                entity.getId(),
                entity.getShowId(),
                entity.getAuditoriumId(),
                entity.getDateTime()
        );
    }
}
