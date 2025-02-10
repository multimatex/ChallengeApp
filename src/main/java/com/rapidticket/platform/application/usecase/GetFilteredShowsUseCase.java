package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.application.dto.FilteredPerformanceDTO;
import com.rapidticket.platform.application.dto.FilteredShowDTO;
import com.rapidticket.platform.domain.model.Performance;
import com.rapidticket.platform.domain.repository.PerformanceRepository;
import com.rapidticket.platform.domain.repository.SeatPriceRepository;
import com.rapidticket.platform.domain.repository.ShowRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GetFilteredShowsUseCase {

    private final ShowRepository showRepository;
    private final PerformanceRepository performanceRepository;
    private final SeatPriceRepository seatPriceRepository;

    public GetFilteredShowsUseCase(ShowRepository showRepository, PerformanceRepository performanceRepository, SeatPriceRepository seatPriceRepository) {
        this.showRepository = showRepository;
        this.performanceRepository = performanceRepository;
        this.seatPriceRepository = seatPriceRepository;
    }

    public Flux<FilteredShowDTO> getFilteredShows(LocalDateTime startDate, LocalDateTime endDate, Double minPrice, Double maxPrice, String sortBy, String order) {
        return showRepository.findAll()
                .flatMap(show -> performanceRepository.findByShowId(show.getId())
                        .filter(performance -> isWithinDateRange(performance, startDate, endDate))
                        .flatMap(performance -> getMinMaxPrice(performance.getShowId())
                                .filter(priceRange -> isWithinPriceRange(priceRange, minPrice, maxPrice))
                                .map(priceRange -> new FilteredPerformanceDTO(
                                        performance.getId(),
                                        performance.getAuditoriumId(),
                                        null,
                                        null,
                                        performance.getDateTime(),
                                        priceRange.get("min"),
                                        priceRange.get("max")
                                )))
                        .collectList()
                        .filter(performances -> !performances.isEmpty())
                        .map(performances -> new FilteredShowDTO(show.getId(), show.getTitle(), show.getDescription(), performances))
                )
                .sort((show1, show2) -> compareShows(show1, show2, sortBy, order));
    }

    private boolean isWithinDateRange(Performance performance, LocalDateTime startDate, LocalDateTime endDate) {
        return (startDate == null || !performance.getDateTime().isBefore(startDate)) &&
                (endDate == null || !performance.getDateTime().isAfter(endDate));
    }

    private boolean isWithinPriceRange(Map<String, Double> priceRange, Double minPrice, Double maxPrice) {
        return (minPrice == null || priceRange.get("min") >= minPrice) &&
                (maxPrice == null || priceRange.get("max") <= maxPrice);
    }

    private Mono<Map<String, Double>> getMinMaxPrice(long showId) {
        return seatPriceRepository.findAll()
                .filter(seatPrice -> seatPrice.getShowId()==showId)
                .collect(Collectors.teeing(
                        Collectors.minBy((sp1, sp2) -> Double.compare(sp1.getPrice(), sp2.getPrice())),
                        Collectors.maxBy((sp1, sp2) -> Double.compare(sp1.getPrice(), sp2.getPrice())),
                        (min, max) -> Map.of("min", min.map(sp -> sp.getPrice()).orElse(0.0), "max", max.map(sp -> sp.getPrice()).orElse(0.0))
                ));
    }

    private int compareShows(FilteredShowDTO show1, FilteredShowDTO show2, String sortBy, String order) {
        int comparison;
        switch (sortBy.toLowerCase()) {
            case "title":
                comparison = show1.getTitle().compareToIgnoreCase(show2.getTitle());
                break;
            case "minprice":
                comparison = Double.compare(show1.getPerformances().get(0).getMinPrice(), show2.getPerformances().get(0).getMinPrice());
                break;
            case "maxprice":
                comparison = Double.compare(show1.getPerformances().get(0).getMaxPrice(), show2.getPerformances().get(0).getMaxPrice());
                break;
            default:
                comparison = 0;
        }
        return "desc".equalsIgnoreCase(order) ? -comparison : comparison;
    }
}

