package com.rapidticket.platform.application.dto;

public class ReservationResponseDTO {
    private long reservationId;
    private String dni;
    private String name;
    private double totalPrice;
    private int totalTickets;

    public ReservationResponseDTO(long reservationId, String dni, String name, double totalPrice, int totalTickets) {
        this.reservationId = reservationId;
        this.dni = dni;
        this.name = name;
        this.totalPrice = totalPrice;
        this.totalTickets = totalTickets;
    }

    public long getReservationId() {
        return reservationId;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTotalTickets() {
        return totalTickets;
    }
}
