package com.rapidticket.platform.infrastructure.adapters.out.dbrepositories;

import com.rapidticket.platform.infrastructure.adapters.out.dbentities.ReservationEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationDBRepository extends ReactiveCrudRepository<ReservationEntity, Long> {


}
