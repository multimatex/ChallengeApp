package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.Auditorium;
import com.rapidticket.platform.domain.repository.AuditoriumRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.AuditoriumEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.AuditoriumDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AuditoriumRepositoryAdapter implements AuditoriumRepository {

    private final AuditoriumDBRepository auditoriumDBRepository;

    public AuditoriumRepositoryAdapter(AuditoriumDBRepository auditoriumDBRepository) {
        this.auditoriumDBRepository = auditoriumDBRepository;
    }

    @Override
    public Mono<Auditorium> save(Auditorium auditorium) {

        AuditoriumEntity entity = toEntity(auditorium);
        return auditoriumDBRepository.save(entity)
                .map(this::toDomain);
    }

    @Override
    public Mono<Auditorium> findById(long id) {
        return auditoriumDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Auditorium> findAll() {
        return auditoriumDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return auditoriumDBRepository.deleteById(id);
    }

    @Override
    public Flux<Auditorium> findByLocationId(long locationId) {
        return auditoriumDBRepository.findByLocationId(locationId)
                .map(this::toDomain);
    }


    private AuditoriumEntity toEntity(Auditorium domain) {
        return new AuditoriumEntity(
                domain.getId(),
                domain.getLocationId(),
                domain.getName(),
                domain.isNumberedSeats()
        );
    }

    private Auditorium toDomain(AuditoriumEntity entity) {
        return new Auditorium(
                entity.getId(),
                entity.getLocationId(),
                entity.getName(),
                Boolean.TRUE.equals(entity.getIsNumberedSeats())
        );
    }
}
