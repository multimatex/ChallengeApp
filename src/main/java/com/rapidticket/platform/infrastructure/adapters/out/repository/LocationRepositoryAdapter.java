package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.Location;
import com.rapidticket.platform.domain.repository.LocationRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.LocationEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.LocationDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class LocationRepositoryAdapter implements LocationRepository {

    private final LocationDBRepository locationDBRepository;

    public LocationRepositoryAdapter(LocationDBRepository locationDBRepository) {
        this.locationDBRepository = locationDBRepository;
    }

    @Override
    public Mono<Location> save(Location location) {
        return locationDBRepository.save(toEntity(location))
                .map(this::toDomain);
    }

    @Override
    public Mono<Location> findById(long id) {
        return locationDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Location> findAll() {
        return locationDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return locationDBRepository.deleteById(id);
    }


    private LocationEntity toEntity(Location domain) {
        if (domain == null) return null;
        return new LocationEntity(
                domain.getId(),
                domain.getName(),
                domain.getAddress(),
                domain.getCountry(),
                domain.getCity()
        );
    }

    private Location toDomain(LocationEntity entity) {
        if (entity == null) return null;
        return new Location(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getCountry(),
                entity.getCity(),
                null
        );
    }
}
