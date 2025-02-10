package com.rapidticket.platform.application.dto;

import java.util.List;

public class AvailableSeatsResponseDTO {
    private long performanceId;
    private long showId;
    private long auditoriumId;
    private String auditoriumName;
    private List<AvailableSeatDTO> availableSeats;

    public AvailableSeatsResponseDTO(long performanceId, long showId, long auditoriumId, String auditoriumName, List<AvailableSeatDTO> availableSeats) {
        this.performanceId = performanceId;
        this.showId = showId;
        this.auditoriumId = auditoriumId;
        this.auditoriumName = auditoriumName;
        this.availableSeats = availableSeats;
    }

    public long getPerformanceId() {
        return performanceId;
    }

    public long getShowId() {
        return showId;
    }

    public long getAuditoriumId() {
        return auditoriumId;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public List<AvailableSeatDTO> getAvailableSeats() {
        return availableSeats;
    }
}

