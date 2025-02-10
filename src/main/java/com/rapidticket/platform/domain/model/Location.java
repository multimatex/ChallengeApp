package com.rapidticket.platform.domain.model;

import java.util.List;

public class Location {
    private long id;
    private String name;
    private String address;
    private String country;
    private String city;

    public Location() {
    }

    public Location(long id, String name, String address, String country, String city, List<Auditorium> auditoriums) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.country = country;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
