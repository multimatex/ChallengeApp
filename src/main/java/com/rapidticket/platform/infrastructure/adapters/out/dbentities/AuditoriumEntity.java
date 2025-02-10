package com.rapidticket.platform.infrastructure.adapters.out.dbentities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("auditorium")
public class AuditoriumEntity {

    @Id
    private long id;

    private long locationId;
    private String name;
    private Boolean isNumberedSeats;

    public AuditoriumEntity() {
    }

    public AuditoriumEntity(long id, long locationId, String name, Boolean isNumberedSeats) {
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

    public Boolean getIsNumberedSeats() {
        return isNumberedSeats;
    }

    public void setIsNumberedSeats(Boolean isNumberedSeats) {
        this.isNumberedSeats = isNumberedSeats;
    }
}