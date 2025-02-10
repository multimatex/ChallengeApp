package com.rapidticket.platform.application.dto;

public class AvailableSeatDTO {
    private long seatId;
    private long seatNumber;
    private long sectionId;
    private double price;

    public AvailableSeatDTO(long seatId, long seatNumber, long sectionId, double price) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.sectionId = sectionId;
        this.price = price;
    }

    public long getSeatId() {
        return seatId;
    }

    public long getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }
}
