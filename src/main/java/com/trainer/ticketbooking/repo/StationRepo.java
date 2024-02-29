package com.trainer.ticketbooking.repo;

import com.trainer.ticketbooking.entity.Station;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StationRepo extends CrudRepository<Station, Long> {
    Optional<Station> findByNameIgnoreCase(String name);
    Optional<Station> findByStationID(long stationID);
}