package com.rapidticket.platform.infrastructure.adapters.out.dbentities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ticket")
public class TicketEntity {

    @Id
    private long id;

    private long reservationId;
    private long performanceId;
    private long showSectionSeatId;

    public TicketEntity() {
    }

    public TicketEntity(long id, long reservationId, long performanceId, long showSectionSeatId) {
        this.id = id;
        this.reservationId = reservationId;
        this.performanceId = performanceId;
        this.showSectionSeatId = showSectionSeatId;
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

    public void setShowSectionSeatId(long showSectionSeatId) {
        this.showSectionSeatId = showSectionSeatId;
    }
}
