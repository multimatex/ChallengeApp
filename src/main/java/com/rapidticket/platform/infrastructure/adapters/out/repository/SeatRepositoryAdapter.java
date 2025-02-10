package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.Seat;
import com.rapidticket.platform.domain.repository.SeatRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.SeatEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.SeatDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SeatRepositoryAdapter implements SeatRepository {

    private final SeatDBRepository seatDBRepository;

    public SeatRepositoryAdapter(SeatDBRepository seatDBRepository) {
        this.seatDBRepository = seatDBRepository;
    }

    @Override
    public Mono<Seat> save(Seat seat) {
        return seatDBRepository.save(toEntity(seat))
                .map(this::toDomain);
    }

    @Override
    public Mono<Seat> findById(long id) {
        return seatDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Seat> findAll() {
        return seatDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return seatDBRepository.deleteById(id);
    }

    @Override
    public Flux<Seat> findByAuditoriumId(long auditoriumId) {
        return seatDBRepository.findAll()
                .filter(seatEntity -> seatEntity.getAuditoriumId() == auditoriumId)
                .map(this::toDomain);
    }


    private SeatEntity toEntity(Seat domain) {
        if (domain == null) return null;
        return new SeatEntity(
                domain.getId(),
                domain.getAuditoriumId(),
                domain.getNumber()
        );
    }

    private Seat toDomain(SeatEntity entity) {
        if (entity == null) return null;
        return new Seat(
                entity.getId(),
                entity.getAuditoriumId(),
                entity.getNumber() != null ? entity.getNumber() : 0
        );
    }
}
