package com.rapidticket.platform.infrastructure.adapters.in.controller;


import com.rapidticket.platform.domain.model.Ticket;
import com.rapidticket.platform.application.usecase.TicketUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketUseCase ticketUseCase;

    public TicketController(TicketUseCase ticketUseCase) {
        this.ticketUseCase = ticketUseCase;
    }

    @GetMapping
    public Flux<Ticket> getAllTickets() {
        return ticketUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Ticket> getTicketById(@PathVariable long id) {
        return ticketUseCase.findById(id);
    }

    @PostMapping
    public Mono<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ticketUseCase.create(ticket);
    }

    @PutMapping("/{id}")
    public Mono<Ticket> updateTicket(@PathVariable long id, @RequestBody Ticket ticket) {
        ticket.setId(id);
        return ticketUseCase.update(ticket);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTicket(@PathVariable long id) {
        return ticketUseCase.deleteById(id);
    }

    @GetMapping("/byReservation/{reservationId}")
    public Flux<Ticket> findByReservationId(@PathVariable long reservationId) {
        return ticketUseCase.findByReservationId(reservationId);
    }
}
