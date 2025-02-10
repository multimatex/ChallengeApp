package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.Ticket;
import com.rapidticket.platform.domain.repository.TicketRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.TicketEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.TicketDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TicketRepositoryAdapter implements TicketRepository {

    private final TicketDBRepository ticketDBRepository;

    public TicketRepositoryAdapter(TicketDBRepository ticketDBRepository) {
        this.ticketDBRepository = ticketDBRepository;
    }

    @Override
    public Mono<Ticket> save(Ticket ticket) {
        return ticketDBRepository.save(toEntity(ticket))
                .map(this::toDomain);
    }

    @Override
    public Mono<Ticket> findById(long id) {
        return ticketDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Ticket> findAll() {
        return ticketDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return ticketDBRepository.deleteById(id);
    }

    @Override
    public Flux<Ticket> findByReservationId(long reservationId) {
        return ticketDBRepository.findByReservationId(reservationId)
                .map(this::toDomain);
    }

    @Override
    public Flux<Ticket> findByShowSectionSeatId(long showSectionSeatId) {
        return ticketDBRepository.findAll()
                .filter(ticketEntity -> ticketEntity.getShowSectionSeatId() == showSectionSeatId)
                .map(this::toDomain);
    }

    @Override
    public Flux<Ticket> findByPerformanceIdAndShowSectionSeatId(long performanceId, long showSectionSeatId) {
        return ticketDBRepository.findAll()
                .filter(ticketEntity -> ticketEntity.getPerformanceId() == performanceId && ticketEntity.getShowSectionSeatId() == showSectionSeatId)
                .map(this::toDomain);
    }


    private TicketEntity toEntity(Ticket domain) {
        if (domain == null) return null;
        return new TicketEntity(
                domain.getId(),
                domain.getReservationId(),
                domain.getPerformanceId(),
                domain.getShowSectionSeatId()
        );
    }

    private Ticket toDomain(TicketEntity entity) {
        if (entity == null) return null;
        return new Ticket(
                entity.getId(),
                entity.getReservationId(),
                entity.getPerformanceId(),
                entity.getShowSectionSeatId()
        );
    }
}