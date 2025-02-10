package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.Reservation;
import com.rapidticket.platform.domain.repository.ReservationRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.ReservationEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.ReservationDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.time.LocalDateTime;


@Component
public class ReservationRepositoryAdapter implements ReservationRepository {

    private final ReservationDBRepository reservationDBRepository;

    public ReservationRepositoryAdapter(ReservationDBRepository reservationDBRepository) {
        this.reservationDBRepository = reservationDBRepository;
    }

    @Override
    public Mono<Reservation> save(Reservation reservation) {
        return reservationDBRepository.save(toEntity(reservation))
                .map(this::toDomain);
    }

    @Override
    public Mono<Reservation> findById(long id) {
        return reservationDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Reservation> findAll() {
        return reservationDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return reservationDBRepository.deleteById(id);
    }

    @Override
    public Flux<Reservation> findBySpectatorId(long spectatorId) {

        return reservationDBRepository.findAll()
                .filter(reservationEntity -> reservationEntity.getSpectatorId() == spectatorId)
                .map(this::toDomain);
    }


    private ReservationEntity toEntity(Reservation domain) {
        if (domain == null) return null;
        LocalDateTime localDateTime = null;
        if (domain.getReservationDate() != null) {
            localDateTime = domain.getReservationDate()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }
        return new ReservationEntity(
                domain.getId(),
                domain.getSpectatorId(),
                localDateTime,
                domain.getTotalPrice()
        );
    }

    private Reservation toDomain(ReservationEntity entity) {
        if (entity == null) return null;
        LocalDateTime date = null;
        if (entity.getReservationDate() != null) {
            date = entity.getReservationDate();
        }
        return new Reservation(
                entity.getId(),
                entity.getSpectatorId(),
                date,
                entity.getTotalPrice()
        );
    }
}
