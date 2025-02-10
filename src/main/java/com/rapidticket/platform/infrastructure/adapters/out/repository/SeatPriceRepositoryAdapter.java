package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.SeatPrice;
import com.rapidticket.platform.domain.repository.SeatPriceRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.SeatPriceEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.SeatPriceDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SeatPriceRepositoryAdapter implements SeatPriceRepository {

    private final SeatPriceDBRepository seatPriceDBRepository;

    public SeatPriceRepositoryAdapter(SeatPriceDBRepository seatPriceDBRepository) {
        this.seatPriceDBRepository = seatPriceDBRepository;
    }

    @Override
    public Mono<SeatPrice> save(SeatPrice seatPrice) {
        return seatPriceDBRepository.save(toEntity(seatPrice))
                .map(this::toDomain);
    }

    @Override
    public Mono<SeatPrice> findById(long id) {
        return seatPriceDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<SeatPrice> findAll() {
        return seatPriceDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return seatPriceDBRepository.deleteById(id);
    }

    @Override
    public Mono<SeatPrice> findByShowIdAndSectionId(long showId, long sectionId) {
        return seatPriceDBRepository.findAll()
                .filter(seatPriceEntity -> seatPriceEntity.getShowId() == showId && seatPriceEntity.getSectionId() == sectionId)
                .next()
                .map(this::toDomain);
    }


    private SeatPriceEntity toEntity(SeatPrice domain) {
        if (domain == null) return null;
        return new SeatPriceEntity(
                domain.getId(),
                domain.getShowId(),
                domain.getSectionId(),
                domain.getPrice()
        );
    }

    private SeatPrice toDomain(SeatPriceEntity entity) {
        if (entity == null) return null;
        return new SeatPrice(
                entity.getId(),
                entity.getShowId(),
                entity.getSectionId(),
                entity.getPrice() != null ? entity.getPrice() : 0.0
        );
    }
}