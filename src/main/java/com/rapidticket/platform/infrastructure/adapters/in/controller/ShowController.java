package com.rapidticket.platform.infrastructure.adapters.in.controller;

import com.rapidticket.platform.domain.model.Show;
import com.rapidticket.platform.application.usecase.ShowUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowUseCase showUseCase;

    public ShowController(ShowUseCase showUseCase) {
        this.showUseCase = showUseCase;
    }

    @GetMapping
    public Flux<Show> getAllShows() {
        return showUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Show> getShowById(@PathVariable long id) {
        return showUseCase.findById(id);
    }

    @PostMapping
    public Mono<Show> createShow(@RequestBody Show show) {
        return showUseCase.create(show);
    }

    @PutMapping("/{id}")
    public Mono<Show> updateShow(@PathVariable long id, @RequestBody Show show) {
        show.setId(id);
        return showUseCase.update(show);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteShow(@PathVariable long id) {
        return showUseCase.deleteById(id);
    }
}
