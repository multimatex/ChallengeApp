package com.rapidticket.platform.infrastructure.adapters.in.controller;


import com.rapidticket.platform.domain.model.Location;
import com.rapidticket.platform.application.usecase.LocationUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationUseCase locationUseCase;

    public LocationController(LocationUseCase locationUseCase) {
        this.locationUseCase = locationUseCase;
    }

    @GetMapping
    public Flux<Location> getAllLocations() {
        return locationUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Location> getLocationById(@PathVariable long id) {
        return locationUseCase.findById(id);
    }

    @PostMapping
    public Mono<Location> createLocation(@RequestBody Location location) {
        return locationUseCase.create(location);
    }

    @PutMapping("/{id}")
    public Mono<Location> updateLocation(@PathVariable long id, @RequestBody Location location) {
        location.setId(id);
        return locationUseCase.update(location);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteLocation(@PathVariable long id) {
        return locationUseCase.deleteById(id);
    }
}
