package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.ShowSectionSeat;
import com.rapidticket.platform.domain.repository.ShowSectionSeatRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.ShowSectionSeatEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.ShowSectionSeatDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ShowSectionSeatRepositoryAdapter implements ShowSectionSeatRepository {

    private final ShowSectionSeatDBRepository showSectionSeatDBRepository;

    public ShowSectionSeatRepositoryAdapter(ShowSectionSeatDBRepository showSectionSeatDBRepository) {
        this.showSectionSeatDBRepository = showSectionSeatDBRepository;
    }

    @Override
    public Mono<ShowSectionSeat> save(ShowSectionSeat showSectionSeat) {
        return showSectionSeatDBRepository.save(toEntity(showSectionSeat))
                .map(this::toDomain);
    }

    @Override
    public Mono<ShowSectionSeat> findById(long id) {
        return showSectionSeatDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<ShowSectionSeat> findAll() {
        return showSectionSeatDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return showSectionSeatDBRepository.deleteById(id);
    }

    @Override
    public Flux<ShowSectionSeat> findByShowId(long showId) {
        return showSectionSeatDBRepository.findAll()
                .filter(showSectionSeatEntity -> showSectionSeatEntity.getShowId() == showId)
                .map(this::toDomain);
    }
    @Override
    public Flux<ShowSectionSeat> findBySectionId(long sectionId) {
        return showSectionSeatDBRepository.findAll()
                .filter(showSectionSeatEntity -> showSectionSeatEntity.getSectionId() == sectionId)
                .map(this::toDomain);
    }

    @Override
    public Flux<ShowSectionSeat> findByShowIdAndSectionId(long showId, long sectionId) {
        return showSectionSeatDBRepository.findAll()
                .filter(showSectionSeatEntity -> showSectionSeatEntity.getShowId() == showId && showSectionSeatEntity.getSectionId() == sectionId)
                .map(this::toDomain);
    }

    @Override
    public Flux<ShowSectionSeat> findByPricesId(long priceId) {
        return showSectionSeatDBRepository.findAll()
                .filter(showSectionSeatEntity -> showSectionSeatEntity.getPricesId() == priceId)
                .map(this::toDomain);
    }


    private ShowSectionSeatEntity toEntity(ShowSectionSeat domain) {
        if (domain == null) return null;
        return new ShowSectionSeatEntity(
                domain.getId(),
                domain.getShowId(),
                domain.getSectionId(),
                domain.getSeatId(),
                domain.getSeatPriceId()
        );
    }

    private ShowSectionSeat toDomain(ShowSectionSeatEntity entity) {
        if (entity == null) return null;
        return new ShowSectionSeat(
                entity.getId(),
                entity.getShowId(),
                entity.getSectionId(),
                entity.getSeatId(),
                entity.getPricesId()
        );
    }
}
