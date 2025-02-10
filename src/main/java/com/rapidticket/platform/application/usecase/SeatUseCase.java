package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.Seat;
import com.rapidticket.platform.domain.repository.SeatRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class SeatUseCase {
    private final SeatRepository seatRepository;

    public SeatUseCase(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Mono<Seat> create(Seat seat) {
        return seatRepository.save(seat);
    }

    public Mono<Seat> update(Seat seat) {
        return seatRepository.save(seat);
    }

    public Mono<Seat> findById(long id) {
        return seatRepository.findById(id);
    }

    public Flux<Seat> findAll() {
        return seatRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return seatRepository.deleteById(id);
    }

    public Flux<Seat> findByAuditoriumId(long auditoriumId) {
        return seatRepository.findByAuditoriumId(auditoriumId);
    }
}
