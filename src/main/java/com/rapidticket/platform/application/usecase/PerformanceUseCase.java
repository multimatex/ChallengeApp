package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.Performance;
import com.rapidticket.platform.domain.repository.PerformanceRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class PerformanceUseCase {
    private final PerformanceRepository performanceRepository;

    public PerformanceUseCase(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    public Mono<Performance> create(Performance performance) {
        return performanceRepository.save(performance);
    }

    public Mono<Performance> update(Performance performance) {
        return performanceRepository.save(performance);
    }

    public Mono<Performance> findById(long id) {
        return performanceRepository.findById(id);
    }

    public Flux<Performance> findAll() {
        return performanceRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return performanceRepository.deleteById(id);
    }

}
