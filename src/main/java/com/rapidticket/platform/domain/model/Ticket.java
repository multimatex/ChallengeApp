package com.rapidticket.platform.domain.model;

public class Ticket {
    private long id;
    private long reservationId;
    private long performanceId;
    private long showSectionSeatId;

    public Ticket() {
    }

    public Ticket(long id, long reservationId, long performanceId, long show_section_seatId) {
        this.id = id;
        this.reservationId = reservationId;
        this.performanceId = performanceId;
        this.showSectionSeatId = show_section_seatId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(long performanceId) {
        this.performanceId = performanceId;
    }

    public long getShowSectionSeatId() {
        return showSectionSeatId;
    }

    public void setShowSectionSeatId(long showsectionseatId) {
        this.showSectionSeatId = showsectionseatId;
    }
}
