package com.rapidticket.platform.infrastructure.adapters.in.controller;


import com.rapidticket.platform.domain.model.ShowSectionSeat;
import com.rapidticket.platform.application.usecase.ShowSectionSeatUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/show-section-seats")
public class ShowSectionSeatController {

    private final ShowSectionSeatUseCase showSectionSeatUseCase;

    public ShowSectionSeatController(ShowSectionSeatUseCase showSectionSeatUseCase) {
        this.showSectionSeatUseCase = showSectionSeatUseCase;
    }

    @GetMapping
    public Flux<ShowSectionSeat> getAllShowSectionSeats() {
        return showSectionSeatUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ShowSectionSeat> getShowSectionSeatById(@PathVariable long id) {
        return showSectionSeatUseCase.findById(id);
    }

    @PostMapping
    public Mono<ShowSectionSeat> createShowSectionSeat(@RequestBody ShowSectionSeat sss) {
        return showSectionSeatUseCase.create(sss);
    }

    @PutMapping("/{id}")
    public Mono<ShowSectionSeat> updateShowSectionSeat(@PathVariable long id, @RequestBody ShowSectionSeat sss) {
        sss.setId(id);
        return showSectionSeatUseCase.update(sss);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteShowSectionSeat(@PathVariable long id) {
        return showSectionSeatUseCase.deleteById(id);
    }

}
