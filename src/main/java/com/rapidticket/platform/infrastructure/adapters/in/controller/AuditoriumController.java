package com.rapidticket.platform.infrastructure.adapters.in.controller;


import com.rapidticket.platform.domain.model.Auditorium;
import com.rapidticket.platform.application.usecase.AuditoriumUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auditoriums")
public class AuditoriumController {

    private final AuditoriumUseCase auditoriumUseCase;

    public AuditoriumController(AuditoriumUseCase auditoriumUseCase) {
        this.auditoriumUseCase = auditoriumUseCase;
    }

    @GetMapping
    public Flux<Auditorium> getAllAuditoriums() {
        return auditoriumUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Auditorium> getAuditoriumById(@PathVariable long id) {
        return auditoriumUseCase.findById(id);
    }

    @PostMapping
    public Mono<Auditorium> createAuditorium(@RequestBody Auditorium auditorium) {
        return auditoriumUseCase.create(auditorium);
    }

    @PutMapping("/{id}")
    public Mono<Auditorium> updateAuditorium(@PathVariable long id, @RequestBody Auditorium auditorium) {
        auditorium.setId(id);
        return auditoriumUseCase.update(auditorium);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAuditorium(@PathVariable long id) {
        return auditoriumUseCase.deleteById(id);
    }

    @GetMapping("/location/{locationId}")
    public Flux<Auditorium> findByLocationId(@PathVariable long locationId) {
        return auditoriumUseCase.findByLocationId(locationId);
    }
}
