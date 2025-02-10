package com.rapidticket.platform.domain.model;

import java.time.LocalDateTime;

public class Reservation {
    private long id;
    private long spectatorId;
    private LocalDateTime reservationDate;
    private Double totalPrice;

    public Reservation() {
    }

    public Reservation(long id, long spectatorId, LocalDateTime reservationDate, Double totalPrice) {
        this.id = id;
        this.spectatorId = spectatorId;
        this.reservationDate = reservationDate;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSpectatorId() {
        return spectatorId;
    }

    public void setSpectatorId(long spectatorId) {
        this.spectatorId = spectatorId;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
