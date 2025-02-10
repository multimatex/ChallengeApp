package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.application.dto.*;
import com.rapidticket.platform.domain.model.*;
import com.rapidticket.platform.domain.repository.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetShowInfoUseCase {

    private final ShowRepository showRepository;
    private final PerformanceRepository performanceRepository;
    private final LocationRepository locationRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final SectionRepository sectionRepository;
    private final SeatRepository seatRepository;
    private final SeatPriceRepository seatPriceRepository;
    private final ReservationRepository reservationRepository;
    private final ShowSectionSeatRepository showSectionSeatRepository;
    private final TicketRepository ticketRepository;

    public GetShowInfoUseCase(ShowRepository showRepository, PerformanceRepository performanceRepository,
                              LocationRepository locationRepository, AuditoriumRepository auditoriumRepository,
                              SectionRepository sectionRepository, SeatRepository seatRepository,
                              SeatPriceRepository seatPriceRepository, ReservationRepository reservationRepository,
                              ShowSectionSeatRepository showSectionSeatRepository,
                              TicketRepository ticketRepository) {
        this.showRepository = showRepository;
        this.performanceRepository = performanceRepository;
        this.locationRepository = locationRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.sectionRepository = sectionRepository;
        this.seatRepository = seatRepository;
        this.seatPriceRepository = seatPriceRepository;
        this.reservationRepository = reservationRepository;
        this.showSectionSeatRepository=showSectionSeatRepository;
        this.ticketRepository = ticketRepository;
    }

    public Flux<ShowResponseDTO> getAllShowInfo() {
        return showRepository.findAll()
                .flatMap(show -> performanceRepository.findByShowId(show.getId())
                        .flatMap(performance -> auditoriumRepository.findById(performance.getAuditoriumId())
                                .flatMap(auditorium -> locationRepository.findById(auditorium.getLocationId())
                                        .flatMap(location -> showSectionSeatRepository.findByShowId(performance.getShowId())
                                                .flatMap(showSectionSeat -> sectionRepository.findById(showSectionSeat.getSectionId())
                                                        .zipWith(seatPriceRepository.findById(showSectionSeat.getSeatPriceId()))
                                                        .flatMap(tuple -> {
                                                            Section section = tuple.getT1();
                                                            SeatPrice seatPrice = tuple.getT2();

                                                            return seatRepository.findById(showSectionSeat.getSeatId())
                                                                    .flatMapMany(seat -> ticketRepository.findByPerformanceIdAndShowSectionSeatId(
                                                                                    performance.getId(), showSectionSeat.getId())
                                                                            .hasElements()
                                                                            .map(isReserved -> new SeatDTO(
                                                                                    seat.getId(),
                                                                                    seat.getNumber(),
                                                                                    isReserved
                                                                            ))
                                                                    )
                                                                    .collectList()
                                                                    .map(seats -> new SectionDTO(
                                                                            section.getId(),
                                                                            section.getName(),
                                                                            seatPrice.getPrice(),
                                                                            seats
                                                                    ));
                                                        })
                                                )
                                                .collectList()
                                                .map(sections -> new PerformanceDTO(
                                                        performance.getId(),
                                                        location.getId(),
                                                        location.getName(),
                                                        auditorium.getId(),
                                                        auditorium.getName(),
                                                        auditorium.isNumberedSeats(),
                                                        performance.getDateTime().toLocalDate().toString(),
                                                        performance.getDateTime().toLocalTime().toString(),
                                                        sections
                                                ))
                                        )
                                )
                        )
                        .collectList()
                        .map(performances -> new ShowResponseDTO(
                                show.getId(),
                                show.getTitle(),
                                show.getDescription(),
                                performances
                        )));
    }
}
