package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.ShowSectionSeat;
import com.rapidticket.platform.domain.repository.ShowSectionSeatRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ShowSectionSeatUseCase {
    private final ShowSectionSeatRepository showSectionSeatRepository;

    public ShowSectionSeatUseCase(ShowSectionSeatRepository showSectionSeatRepository) {
        this.showSectionSeatRepository = showSectionSeatRepository;
    }

    public Mono<ShowSectionSeat> create(ShowSectionSeat sss) {
        return showSectionSeatRepository.save(sss);
    }

    public Mono<ShowSectionSeat> update(ShowSectionSeat sss) {
        return showSectionSeatRepository.save(sss);
    }

    public Mono<ShowSectionSeat> findById(long id) {
        return showSectionSeatRepository.findById(id);
    }

    public Flux<ShowSectionSeat> findAll() {
        return showSectionSeatRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return showSectionSeatRepository.deleteById(id);
    }

}
