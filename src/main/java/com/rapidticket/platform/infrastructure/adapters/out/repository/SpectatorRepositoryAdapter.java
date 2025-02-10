package com.rapidticket.platform.infrastructure.adapters.out.repository;

import com.rapidticket.platform.domain.model.Spectator;
import com.rapidticket.platform.domain.repository.SpectatorRepository;
import com.rapidticket.platform.infrastructure.adapters.out.dbentities.SpectatorEntity;
import com.rapidticket.platform.infrastructure.adapters.out.dbrepositories.SpectatorDBRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SpectatorRepositoryAdapter implements SpectatorRepository {

    private final SpectatorDBRepository spectatorDBRepository;

    public SpectatorRepositoryAdapter(SpectatorDBRepository spectatorDBRepository) {
        this.spectatorDBRepository = spectatorDBRepository;
    }

    @Override
    public Mono<Spectator> save(Spectator spectator) {
        return spectatorDBRepository.save(toEntity(spectator))
                .map(this::toDomain);
    }

    @Override
    public Mono<Spectator> findById(long id) {
        return spectatorDBRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Spectator> findAll() {
        return spectatorDBRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return spectatorDBRepository.deleteById(id);
    }

    @Override
    public Mono<Spectator> findByDni(String dni) {
        return spectatorDBRepository.findByDni(dni)
                .map(this::toDomain);
    }


    private SpectatorEntity toEntity(Spectator domain) {
        if (domain == null) return null;
        return new SpectatorEntity(
                domain.getId(),
                domain.getDni(),
                domain.getName()
        );
    }

    private Spectator toDomain(SpectatorEntity entity) {
        if (entity == null) return null;
        return new Spectator(
                entity.getId(),
                entity.getDni(),
                entity.getName()
        );
    }
}
