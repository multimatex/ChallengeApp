package com.rapidticket.platform.infrastructure.adapters.in.controller;


import com.rapidticket.platform.domain.model.Performance;
import com.rapidticket.platform.application.usecase.PerformanceUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/performances")
public class PerformanceController {

    private final PerformanceUseCase performanceUseCase;

    public PerformanceController(PerformanceUseCase performanceUseCase) {
        this.performanceUseCase = performanceUseCase;
    }

    @GetMapping
    public Flux<Performance> getAllPerformances() {
        return performanceUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Performance> getPerformanceById(@PathVariable long id) {
        return performanceUseCase.findById(id);
    }

    @PostMapping
    public Mono<Performance> createPerformance(@RequestBody Performance performance) {
        return performanceUseCase.create(performance);
    }

    @PutMapping("/{id}")
    public Mono<Performance> updatePerformance(@PathVariable long id, @RequestBody Performance performance) {
        performance.setId(id);
        return performanceUseCase.update(performance);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePerformance(@PathVariable long id) {
        return performanceUseCase.deleteById(id);
    }
}
