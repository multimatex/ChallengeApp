package com.rapidticket.platform.infrastructure.adapters.out.dbentities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("reservation")
public class ReservationEntity {

    @Id
    private long id;

    private long spectatorId;
    private LocalDateTime reservationDate;
    private Double totalPrice;

    public ReservationEntity() {
    }

    public ReservationEntity(long id, long spectatorId, LocalDateTime reservationDate, Double totalPrice) {
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
