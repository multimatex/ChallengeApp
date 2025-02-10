package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.application.dto.*;
import com.rapidticket.platform.domain.model.*;
import com.rapidticket.platform.domain.repository.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
public class CreateReservationUseCase {

    private final SpectatorRepository spectatorRepository;
    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;
    private final ShowSectionSeatRepository showSectionSeatRepository;
    private final SeatPriceRepository seatPriceRepository;

    public CreateReservationUseCase(SpectatorRepository spectatorRepository,
                                    ReservationRepository reservationRepository,
                                    TicketRepository ticketRepository,
                                    ShowSectionSeatRepository showSectionSeatRepository,
                                    SeatPriceRepository seatPriceRepository) {
        this.spectatorRepository = spectatorRepository;
        this.reservationRepository = reservationRepository;
        this.ticketRepository = ticketRepository;
        this.showSectionSeatRepository = showSectionSeatRepository;
        this.seatPriceRepository = seatPriceRepository;
    }

    public Mono<ReservationResponseDTO> createReservation(ReservationRequestDTO request) {
        Mono<Spectator> spectatorMono = spectatorRepository.findByDni(request.getDni())
                .switchIfEmpty(spectatorRepository.save(new Spectator(0, request.getDni(), request.getName())));

        Mono<Double> totalPriceMono = Flux.fromIterable(request.getSeats())
                .flatMap(seatRequest ->
                        showSectionSeatRepository.findByShowIdAndSectionId(seatRequest.getShowId(), seatRequest.getSectionId())
                                .filter(showSectionSeat -> showSectionSeat.getSeatId() == seatRequest.getSeatId())
                                .singleOrEmpty()
                                .flatMap(showSectionSeat -> seatPriceRepository.findById(showSectionSeat.getSeatPriceId()))
                                .map(SeatPrice::getPrice)
                )
                .reduce(Double::sum);

        Mono<Reservation> reservationMono = spectatorMono.flatMap(spectator ->
                totalPriceMono.flatMap(totalPrice -> {
                    Reservation reservation = new Reservation(0, spectator.getId(), LocalDateTime.now(), totalPrice);
                    return reservationRepository.save(reservation);
                })
        );

        return reservationMono.flatMap(reservation ->
                Flux.fromIterable(request.getSeats())
                        .flatMap(seatRequest ->
                                showSectionSeatRepository.findByShowIdAndSectionId(seatRequest.getShowId(), seatRequest.getSectionId())
                                        .filter(showSectionSeat -> showSectionSeat.getSeatId() == seatRequest.getSeatId())
                                        .singleOrEmpty()
                                        .flatMap(showSectionSeat -> ticketRepository.save(new Ticket(0, reservation.getId(), request.getPerformanceId(), showSectionSeat.getId())))
                        )
                        .collectList()
                        .map(tickets -> new ReservationResponseDTO(reservation.getId(), request.getDni(), request.getName(), reservation.getTotalPrice(), tickets.size()))
        );
    }
}
