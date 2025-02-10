package com.rapidticket.platform.application.dto;

import java.util.List;

public class SectionDTO {
    private long id;
    private String name;
    private double price;
    private List<SeatDTO> seats;

    public SectionDTO(long id, String name, double price, List<SeatDTO> seats) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }
}
