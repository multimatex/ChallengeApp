package com.rapidticket.platform.infrastructure.adapters.in.controller;


import com.rapidticket.platform.domain.model.Seat;
import com.rapidticket.platform.application.usecase.SeatUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatUseCase seatUseCase;

    public SeatController(SeatUseCase seatUseCase) {
        this.seatUseCase = seatUseCase;
    }

    @GetMapping
    public Flux<Seat> getAllSeats() {
        return seatUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Seat> getSeatById(@PathVariable long id) {
        return seatUseCase.findById(id);
    }

    @PostMapping
    public Mono<Seat> createSeat(@RequestBody Seat seat) {
        return seatUseCase.create(seat);
    }

    @PutMapping("/{id}")
    public Mono<Seat> updateSeat(@PathVariable long id, @RequestBody Seat seat) {
        seat.setId(id);
        return seatUseCase.update(seat);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteSeat(@PathVariable long id) {
        return seatUseCase.deleteById(id);
    }

    @GetMapping("/auditorium/{auditoriumId}")
    public Flux<Seat> getByAuditorium(@PathVariable long auditoriumId) {
        return seatUseCase.findByAuditoriumId(auditoriumId);
    }
}