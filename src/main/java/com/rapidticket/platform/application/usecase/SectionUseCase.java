package com.rapidticket.platform.application.usecase;

import com.rapidticket.platform.domain.model.Section;
import com.rapidticket.platform.domain.repository.SectionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class SectionUseCase {
    private final SectionRepository sectionRepository;

    public SectionUseCase(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Mono<Section> create(Section section) {
        return sectionRepository.save(section);
    }

    public Mono<Section> update(Section section) {
        return sectionRepository.save(section);
    }

    public Mono<Section> findById(long id) {
        return sectionRepository.findById(id);
    }

    public Flux<Section> findAll() {
        return sectionRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return sectionRepository.deleteById(id);
    }
}
