package com.rapidticket.platform.application.dto;

public class SeatDTO {
    private long seatId;
    private long seatNumber;
    private boolean reserved;

    public SeatDTO(long seatId, long seatNumber, boolean reserved) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.reserved = reserved;
    }

    public long getSeatId() {
        return seatId;
    }

    public long getSeatNumber() {
        return seatNumber;
    }

    public boolean isReserved() {
        return reserved;
    }
}
