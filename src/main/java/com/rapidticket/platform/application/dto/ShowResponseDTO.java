package com.rapidticket.platform.application.dto;

import java.util.List;

public class ShowResponseDTO {
    private long showId;
    private String name;
    private String desc;
    private List<PerformanceDTO> performances;

    public ShowResponseDTO(long showId, String name, String desc, List<PerformanceDTO> performances) {
        this.showId = showId;
        this.name = name;
        this.desc = desc;
        this.performances = performances;
    }

    public long getShowId() {
        return showId;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public List<PerformanceDTO> getPerformances() {
        return performances;
    }
}
