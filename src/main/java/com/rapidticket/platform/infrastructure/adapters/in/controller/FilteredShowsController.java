package com.rapidticket.platform.infrastructure.adapters.in.controller;

import com.rapidticket.platform.application.dto.FilteredShowDTO;
import com.rapidticket.platform.application.usecase.GetFilteredShowsUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/shows")
public class FilteredShowsController {

    private final GetFilteredShowsUseCase getFilteredShowsUseCase;

    public FilteredShowsController(GetFilteredShowsUseCase getFilteredShowsUseCase) {
        this.getFilteredShowsUseCase = getFilteredShowsUseCase;
    }

    @GetMapping("/filtered")
    public Flux<FilteredShowDTO> getFilteredShows(
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return getFilteredShowsUseCase.getFilteredShows(startDate, endDate, minPrice, maxPrice, sortBy, order);
    }
}
