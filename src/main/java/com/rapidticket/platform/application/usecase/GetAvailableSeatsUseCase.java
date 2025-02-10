package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.application.dto.AvailableSeatDTO;
import com.rapidticket.platform.application.dto.AvailableSeatsResponseDTO;
import com.rapidticket.platform.domain.repository.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetAvailableSeatsUseCase {

    private final PerformanceRepository performanceRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final SeatRepository seatRepository;
    private final SeatPriceRepository seatPriceRepository;
    private final ReservationRepository reservationRepository;
    private final SectionRepository sectionRepository;
    private final LocationRepository locationRepository;
    private final ShowSectionSeatRepository showSectionSeatRepository;
    private final TicketRepository ticketRepository;

    public GetAvailableSeatsUseCase(PerformanceRepository performanceRepository, AuditoriumRepository auditoriumRepository,
                                    SeatRepository seatRepository, SeatPriceRepository seatPriceRepository,
                                    ReservationRepository reservationRepository,
                                    SectionRepository sectionRepository,
                                    LocationRepository locationRepository,
                                    ShowSectionSeatRepository showSectionSeatRepository,
                                    TicketRepository ticketRepository
                                    ) {
        this.performanceRepository = performanceRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.seatRepository = seatRepository;
        this.seatPriceRepository = seatPriceRepository;
        this.reservationRepository = reservationRepository;
        this.sectionRepository = sectionRepository;
        this.locationRepository = locationRepository;
        this.showSectionSeatRepository = showSectionSeatRepository;
        this.ticketRepository = ticketRepository;
    }

    public Mono<AvailableSeatsResponseDTO> getAvailableSeatsForPerformance(long performanceId) {
        return performanceRepository.findById(performanceId)
                .flatMap(performance -> auditoriumRepository.findById(performance.getAuditoriumId())
                        .flatMap(auditorium -> showSectionSeatRepository.findByShowId(performance.getShowId())
                                .flatMap(showSectionSeat -> seatRepository.findById(showSectionSeat.getSeatId())
                                        .flatMap(seat -> ticketRepository.findByPerformanceIdAndShowSectionSeatId(
                                                        performance.getId(), showSectionSeat.getId())
                                                .collectList()
                                                .map(tickets -> tickets.isEmpty())
                                                .filter(isReserved -> !isReserved)
                                                .map(isReserved -> new AvailableSeatDTO(
                                                        seat.getId(),
                                                        seat.getNumber(),
                                                        showSectionSeat.getSectionId(),
                                                        showSectionSeat.getSeatPriceId()
                                                ))
                                        )
                                )
                                .collectList()
                                .map(availableSeats -> new AvailableSeatsResponseDTO(
                                        performance.getId(),
                                        performance.getShowId(),
                                        auditorium.getId(),
                                        auditorium.getName(),
                                        availableSeats
                                ))
                        )
                );
    }
}

