package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.Show;
import com.rapidticket.platform.domain.repository.ShowRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class ShowUseCase {
    private final ShowRepository showRepository;

    public ShowUseCase(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public Mono<Show> create(Show show) {
        return showRepository.save(show);
    }

    public Mono<Show> update(Show show) {
        return showRepository.save(show);
    }

    public Mono<Show> findById(long id) {
        return showRepository.findById(id);
    }

    public Flux<Show> findAll() {
        return showRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return showRepository.deleteById(id);
    }
}