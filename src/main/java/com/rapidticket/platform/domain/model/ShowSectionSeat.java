package com.rapidticket.platform.domain.model;

public class ShowSectionSeat {
    private long id;
    private long showId;
    private long sectionId;
    private long seatId;
    private long seatPriceId;

    public ShowSectionSeat() {
    }

    public ShowSectionSeat(long id, long showId, long sectionId, long seatId, long seatPriceId) {
        this.id = id;
        this.showId = showId;
        this.sectionId = sectionId;
        this.seatId = seatId;
        this.seatPriceId = seatPriceId;
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

    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    public long getSeatPriceId() {
        return seatPriceId;
    }

    public void setSeatPriceId(long seatPriceId) {
        this.seatPriceId = seatPriceId;
    }
}
