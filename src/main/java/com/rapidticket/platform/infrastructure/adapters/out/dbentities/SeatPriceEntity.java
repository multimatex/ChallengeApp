package com.rapidticket.platform.infrastructure.adapters.out.dbentities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("seat_price")
public class SeatPriceEntity {

    @Id
    private long id;

    private long showId;
    private long sectionId;
    private Double price;

    public SeatPriceEntity() {
    }

    public SeatPriceEntity(long id, long showId, long sectionId, Double price) {
        this.id = id;
        this.showId = showId;
        this.sectionId = sectionId;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
