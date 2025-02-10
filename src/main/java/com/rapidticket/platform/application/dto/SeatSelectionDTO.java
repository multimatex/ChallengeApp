package com.rapidticket.platform.application.dto;

public class SeatSelectionDTO {
    private long showId;
    private long sectionId;
    private long seatId;

    public SeatSelectionDTO(long showId, long sectionId, long seatId) {
        this.showId = showId;
        this.sectionId = sectionId;
        this.seatId = seatId;
    }

    public long getShowId() {
        return showId;
    }

    public long getSectionId() {
        return sectionId;
    }

    public long getSeatId() {
        return seatId;
    }
}

