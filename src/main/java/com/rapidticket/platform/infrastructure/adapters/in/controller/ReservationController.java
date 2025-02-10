package com.rapidticket.platform.infrastructure.adapters.in.controller;


import com.rapidticket.platform.domain.model.Reservation;
import com.rapidticket.platform.application.usecase.ReservationUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationUseCase reservationUseCase;

    public ReservationController(ReservationUseCase reservationUseCase) {
        this.reservationUseCase = reservationUseCase;
    }

    @GetMapping
    public Flux<Reservation> getAllReservations() {
        return reservationUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Reservation> getReservationById(@PathVariable long id) {
        return reservationUseCase.findById(id);
    }

    @PostMapping
    public Mono<Reservation> createReservation(@RequestBody Reservation reservation) {
        return reservationUseCase.create(reservation);
    }

    @PutMapping("/{id}")
    public Mono<Reservation> updateReservation(@PathVariable long id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        return reservationUseCase.update(reservation);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteReservation(@PathVariable long id) {
        return reservationUseCase.deleteById(id);
    }

    @GetMapping("/spectator/{spectatorId}")
    public Flux<Reservation> getBySpectatorId(@PathVariable long spectatorId) {
        return reservationUseCase.findBySpectatorId(spectatorId);
    }
}
