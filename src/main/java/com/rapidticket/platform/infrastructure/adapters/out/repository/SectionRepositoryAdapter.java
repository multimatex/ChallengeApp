package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.Section;
import com.rapidticket.platform.domain.repository.SectionRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.SectionEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.SectionDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SectionRepositoryAdapter implements SectionRepository {

    private final SectionDBRepository sectionDBRepository;

    public SectionRepositoryAdapter(SectionDBRepository sectionDBRepository) {
        this.sectionDBRepository = sectionDBRepository;
    }

    @Override
    public Mono<Section> save(Section section) {
        return sectionDBRepository.save(toEntity(section))
                .map(this::toDomain);
    }

    @Override
    public Mono<Section> findById(long id) {
        return sectionDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Section> findAll() {
        return sectionDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return sectionDBRepository.deleteById(id);
    }


    private SectionEntity toEntity(Section domain) {
        if (domain == null) return null;
        return new SectionEntity(
                domain.getId(),
                domain.getName()
        );
    }

    private Section toDomain(SectionEntity entity) {
        if (entity == null) return null;
        return new Section(
                entity.getId(),
                entity.getName()
        );
    }
}
