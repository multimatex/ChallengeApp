package com.rapidticket.platform.infrastructure.adapters.in.controller;

import com.rapidticket.platform.application.dto.ReservationRequestDTO;
import com.rapidticket.platform.application.dto.ReservationResponseDTO;
import com.rapidticket.platform.application.usecase.CreateReservationUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/reservations")
public class CreateReservationController {

    private final CreateReservationUseCase createReservationUseCase;

    public CreateReservationController(CreateReservationUseCase createReservationUseCase) {
        this.createReservationUseCase = createReservationUseCase;
    }

    @PostMapping("/new")
    public Mono<ReservationResponseDTO> createReservation(@RequestBody ReservationRequestDTO request) {
        return createReservationUseCase.createReservation(request);
    }
}