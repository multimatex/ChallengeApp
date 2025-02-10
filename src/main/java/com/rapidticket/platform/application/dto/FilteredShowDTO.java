package com.rapidticket.platform.application.dto;

import java.util.List;

public class FilteredShowDTO {
    private long showId;
    private String title;
    private String description;
    private List<FilteredPerformanceDTO> performances;

    public FilteredShowDTO(long showId, String title, String description, List<FilteredPerformanceDTO> performances) {
        this.showId = showId;
        this.title = title;
        this.description = description;
        this.performances = performances;
    }

    public long getShowId() {
        return showId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<FilteredPerformanceDTO> getPerformances() {
        return performances;
    }
}
