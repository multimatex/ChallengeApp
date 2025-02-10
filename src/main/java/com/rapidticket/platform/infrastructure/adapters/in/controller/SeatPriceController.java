package com.rapidticket.platform.infrastructure.adapters.in.controller;

import com.rapidticket.platform.domain.model.SeatPrice;
import com.rapidticket.platform.application.usecase.SeatPriceUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/seatprices")
public class SeatPriceController {

    private final SeatPriceUseCase seatPriceUseCase;

    public SeatPriceController(SeatPriceUseCase seatPriceUseCase) {
        this.seatPriceUseCase = seatPriceUseCase;
    }

    @GetMapping
    public Flux<SeatPrice> getAllSeatPrices() {
        return seatPriceUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<SeatPrice> getSeatPriceById(@PathVariable long id) {
        return seatPriceUseCase.findById(id);
    }

    @PostMapping
    public Mono<SeatPrice> createSeatPrice(@RequestBody SeatPrice seatPrice) {
        return seatPriceUseCase.create(seatPrice);
    }

    @PutMapping("/{id}")
    public Mono<SeatPrice> updateSeatPrice(@PathVariable long id, @RequestBody SeatPrice seatPrice) {
        seatPrice.setId(id);
        return seatPriceUseCase.update(seatPrice);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteSeatPrice(@PathVariable long id) {
        return seatPriceUseCase.deleteById(id);
    }

    @GetMapping("/byShowAndSection")
    public Mono<SeatPrice> getByShowAndSection(
            @RequestParam long showId,
            @RequestParam long sectionId
    ) {
        return seatPriceUseCase.findByShowIdAndSectionId(showId, sectionId);
    }
}
