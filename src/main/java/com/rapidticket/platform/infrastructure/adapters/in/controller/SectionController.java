package com.rapidticket.platform.infrastructure.adapters.in.controller;


import com.rapidticket.platform.domain.model.Section;
import com.rapidticket.platform.application.usecase.SectionUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    private final SectionUseCase sectionUseCase;

    public SectionController(SectionUseCase sectionUseCase) {
        this.sectionUseCase = sectionUseCase;
    }

    @GetMapping
    public Flux<Section> getAllSections() {
        return sectionUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Section> getSectionById(@PathVariable long id) {
        return sectionUseCase.findById(id);
    }

    @PostMapping
    public Mono<Section> createSection(@RequestBody Section section) {
        return sectionUseCase.create(section);
    }

    @PutMapping("/{id}")
    public Mono<Section> updateSection(@PathVariable long id, @RequestBody Section section) {
        section.setId(id);
        return sectionUseCase.update(section);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteSection(@PathVariable long id) {
        return sectionUseCase.deleteById(id);
    }
}