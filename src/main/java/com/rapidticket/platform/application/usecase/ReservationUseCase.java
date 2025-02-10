package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.Reservation;
import com.rapidticket.platform.domain.repository.ReservationRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class ReservationUseCase {
    private final ReservationRepository reservationRepository;

    public ReservationUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Mono<Reservation> create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Mono<Reservation> update(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Mono<Reservation> findById(long id) {
        return reservationRepository.findById(id);
    }

    public Flux<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return reservationRepository.deleteById(id);
    }

    public Flux<Reservation> findBySpectatorId(long spectatorId) {
        return reservationRepository.findBySpectatorId(spectatorId);
    }
}
