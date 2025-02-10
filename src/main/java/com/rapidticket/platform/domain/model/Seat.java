package com.rapidticket.platform.domain.model;

public class Seat {
    private long id;
    private long auditoriumId;
    private int number;

    public Seat() {
    }

    public Seat(long id, long auditoriumId, int number) {
        this.id = id;
        this.auditoriumId = auditoriumId;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
