package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.SeatPrice;
import com.rapidticket.platform.domain.repository.SeatPriceRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class SeatPriceUseCase {
    private final SeatPriceRepository seatPriceRepository;

    public SeatPriceUseCase(SeatPriceRepository seatPriceRepository) {
        this.seatPriceRepository = seatPriceRepository;
    }

    public Mono<SeatPrice> create(SeatPrice seatPrice) {
        return seatPriceRepository.save(seatPrice);
    }

    public Mono<SeatPrice> update(SeatPrice seatPrice) {
        return seatPriceRepository.save(seatPrice);
    }

    public Mono<SeatPrice> findById(long id) {
        return seatPriceRepository.findById(id);
    }

    public Flux<SeatPrice> findAll() {
        return seatPriceRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return seatPriceRepository.deleteById(id);
    }

    public Mono<SeatPrice> findByShowIdAndSectionId(long showId, long sectionId) {
        return seatPriceRepository.findByShowIdAndSectionId(showId, sectionId);
    }
}
