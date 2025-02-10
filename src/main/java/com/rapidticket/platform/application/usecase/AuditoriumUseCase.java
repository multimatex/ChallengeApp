package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.Auditorium;
import com.rapidticket.platform.domain.repository.AuditoriumRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class AuditoriumUseCase {
    private final AuditoriumRepository auditoriumRepository;

    public AuditoriumUseCase(AuditoriumRepository auditoriumRepository) {
        this.auditoriumRepository = auditoriumRepository;
    }
    public Mono<Auditorium> create(Auditorium auditorium) {
        return auditoriumRepository.save(auditorium);
    }
    public Mono<Auditorium> update(Auditorium auditorium) {
        return auditoriumRepository.save(auditorium);
    }
    public Mono<Auditorium> findById(long id) {
        return auditoriumRepository.findById(id);
    }

    public Flux<Auditorium> findAll() {
        return auditoriumRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return auditoriumRepository.deleteById(id);
    }

    public Flux<Auditorium> findByLocationId(long locationId) {
        return auditoriumRepository.findByLocationId(locationId);
    }
}
