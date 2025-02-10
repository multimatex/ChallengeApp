package com.rapidticket.platform.application.dto;

import java.util.List;

public class ReservationRequestDTO {
    private String dni;
    private String name;
    private long performanceId;
    private List<SeatSelectionDTO> seats;

    public ReservationRequestDTO(String dni, String name, long performanceId, List<SeatSelectionDTO> seats) {
        this.dni = dni;
        this.name = name;
        this.performanceId = performanceId;
        this.seats = seats;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public long getPerformanceId() {
        return performanceId;
    }

    public List<SeatSelectionDTO> getSeats() {
        return seats;
    }
}
