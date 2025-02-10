package com.rapidticket.platform.infrastructure.adapters.in.controller;

import com.rapidticket.platform.application.dto.AvailableSeatsResponseDTO;
import com.rapidticket.platform.application.usecase.GetAvailableSeatsUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/shows")
public class AvailableSeatsController {

    private final GetAvailableSeatsUseCase getAvailableSeatsUseCase;

    public AvailableSeatsController(GetAvailableSeatsUseCase getAvailableSeatsUseCase) {
        this.getAvailableSeatsUseCase = getAvailableSeatsUseCase;
    }

    @GetMapping("/{performanceId}/available-seats")
    public Mono<AvailableSeatsResponseDTO> getAvailableSeats(@PathVariable long performanceId) {
        return getAvailableSeatsUseCase.getAvailableSeatsForPerformance(performanceId);
    }
}