package com.rapidticket.platform.infrastructure.adapters.out.dbentities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("show_section_seat")
public class ShowSectionSeatEntity {

    @Id
    private long id;

    private long showId;
    private long sectionId;
    private long seatId;
    private long seatPriceId;

    public ShowSectionSeatEntity() {
    }

    public ShowSectionSeatEntity(long id, long showId, long sectionId, long seatId, long seatPriceId) {
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

    public long getPricesId() {
        return seatPriceId;
    }

    public void setPricesId(long seatPriceId) {
        this.seatPriceId = seatPriceId;
    }
}
