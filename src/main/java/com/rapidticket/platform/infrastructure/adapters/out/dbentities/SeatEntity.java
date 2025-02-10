package com.rapidticket.platform.infrastructure.adapters.out.dbentities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("seat")
public class SeatEntity {

    @Id
    private long id;

    private long auditoriumId;
    private Integer number;

    public SeatEntity() {
    }

    public SeatEntity(long id, long auditoriumId, Integer number) {
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
