package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.Show;
import com.rapidticket.platform.domain.repository.ShowRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.ShowEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.ShowDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ShowRepositoryAdapter implements ShowRepository {

    private final ShowDBRepository showDBRepository;

    public ShowRepositoryAdapter(ShowDBRepository showDBRepository) {
        this.showDBRepository = showDBRepository;
    }

    @Override
    public Mono<Show> save(Show show) {
        return showDBRepository.save(toEntity(show))
                .map(this::toDomain);
    }

    @Override
    public Mono<Show> findById(long id) {
        return showDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Show> findAll() {
        return showDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return showDBRepository.deleteById(id);
    }


    private ShowEntity toEntity(Show domain) {
        if (domain == null) return null;
        return new ShowEntity(
                domain.getId(),
                domain.getTitle(),
                domain.getDescription()
        );
    }

    private Show toDomain(ShowEntity entity) {
        if (entity == null) return null;
        return new Show(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription()
        );
    }
}
