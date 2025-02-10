package com.rapidticket.platform.application.dto;

import java.util.List;

public class PerformanceDTO {
    private long performanceId;
    private long locationID;
    private String locationName;
    private long auditoriumId;
    private String auditoriumName;
    private boolean auditoriumHasNumberedChairs;
    private String date;
    private String time;
    private List<SectionDTO> sections;

    public PerformanceDTO(long performanceId, long locationID, String locationName, long auditoriumId,
                          String auditoriumName, boolean auditoriumHasNumberedChairs, String date, String time,
                          List<SectionDTO> sections) {
        this.performanceId = performanceId;
        this.locationID = locationID;
        this.locationName = locationName;
        this.auditoriumId = auditoriumId;
        this.auditoriumName = auditoriumName;
        this.auditoriumHasNumberedChairs = auditoriumHasNumberedChairs;
        this.date = date;
        this.time = time;
        this.sections = sections;
    }

    public long getPerformanceId() {
        return performanceId;
    }

    public long getLocationID() {
        return locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public long getAuditoriumId() {
        return auditoriumId;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public boolean isAuditoriumHasNumberedChairs() {
        return auditoriumHasNumberedChairs;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<SectionDTO> getSections() {
        return sections;
    }
}
