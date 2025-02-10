package com.rapidticket.platform.domain.model;

public class SeatPrice {
    private long id;
    private long showId;
    private long sectionId;
    private double Price;

    public SeatPrice() {
    }

    public SeatPrice(long id, long showId, long sectionId, double price) {
        this.id = id;
        this.showId = showId;
        this.sectionId = sectionId;
        Price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShowId() {
        return showId;
    }

    public void setShowId(long showId) {
        this.showId = showId;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
