package com.rapidticket.platform.infrastructure.adapters.out.dbrepositories;

import com.rapidticket.platform.infrastructure.adapters.out.dbentities.SeatPriceEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeatPriceDBRepository extends ReactiveCrudRepository<SeatPriceEntity, Long> {



}