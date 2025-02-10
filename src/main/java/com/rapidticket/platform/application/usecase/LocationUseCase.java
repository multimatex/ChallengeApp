package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.Location;
import com.rapidticket.platform.domain.repository.LocationRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class LocationUseCase {
    private final LocationRepository locationRepository;

    public LocationUseCase(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Mono<Location> create(Location location) {
        return locationRepository.save(location);
    }

    public Mono<Location> update(Location location) {
        return locationRepository.save(location);
    }

    public Mono<Location> findById(long id) {
        return locationRepository.findById(id);
    }

    public Flux<Location> findAll() {
        return locationRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return locationRepository.deleteById(id);
    }
}
