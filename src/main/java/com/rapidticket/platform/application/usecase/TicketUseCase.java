package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.Ticket;
import com.rapidticket.platform.domain.repository.TicketRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TicketUseCase {
    private final TicketRepository ticketRepository;

    public TicketUseCase(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Mono<Ticket> create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Mono<Ticket> update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Mono<Ticket> findById(long id) {
        return ticketRepository.findById(id);
    }

    public Flux<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return ticketRepository.deleteById(id);
    }

    public Flux<Ticket> findByReservationId(long reservationId) {
        return ticketRepository.findByReservationId(reservationId);
    }
}
