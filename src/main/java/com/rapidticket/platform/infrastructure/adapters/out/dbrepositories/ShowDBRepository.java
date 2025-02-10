package com.rapidticket.platform.infrastructure.adapters.out.dbrepositories;

import com.rapidticket.platform.infrastructure.adapters.out.dbentities.ShowEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShowDBRepository extends ReactiveCrudRepository<ShowEntity, Long> {

}
