package com.rapidticket.platform.infrastructure.adapters.out.dbentities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("performance")
public class PerformanceEntity {

    @Id
    private long id;

    private long showId;
    private long auditoriumId;
    private LocalDateTime dateTime;

    public PerformanceEntity() {
    }

    public PerformanceEntity(long id, long showId, long auditoriumId, LocalDateTime dateTime) {
        this.id = id;
        this.showId = showId;
        this.auditoriumId = auditoriumId;
        this.dateTime = dateTime;
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

    public long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}