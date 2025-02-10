package com.rapidticket.platform.domain.model;

public class Auditorium {
    private long id;
    private long locationId;
    private String name;
    private boolean isNumberedSeats;

    public Auditorium() {
    }

    public Auditorium(long id, long locationId, String name, boolean isNumberedSeats) {
        this.id = id;
        this.locationId = locationId;
        this.name = name;
        this.isNumberedSeats = isNumberedSeats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNumberedSeats() {
        return isNumberedSeats;
    }

    public void setNumberedSeats(boolean numberedSeats) {
        isNumberedSeats = numberedSeats;
    }
}

