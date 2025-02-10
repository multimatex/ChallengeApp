package com.rapidticket.platform.application.dto;

import java.time.LocalDateTime;

public class FilteredPerformanceDTO {
    private long performanceId;
    private long auditoriumId;
    private Long locationId;
    private String locationName;
    private LocalDateTime dateTime;
    private double minPrice;
    private double maxPrice;

    public FilteredPerformanceDTO(long performanceId, long auditoriumId, Long locationId, String locationName, LocalDateTime dateTime, double minPrice, double maxPrice) {
        this.performanceId = performanceId;
        this.auditoriumId = auditoriumId;
        this.locationId = locationId;
        this.locationName = locationName;
        this.dateTime = dateTime;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public long getPerformanceId() {
        return performanceId;
    }

    public long getAuditoriumId() {
        return auditoriumId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }
}

