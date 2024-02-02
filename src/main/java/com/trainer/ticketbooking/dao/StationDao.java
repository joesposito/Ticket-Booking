package com.trainer.ticketbooking.dao;

import com.trainer.ticketbooking.entity.LocalUser;
import com.trainer.ticketbooking.entity.Station;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StationDao extends CrudRepository<Station, Long> {
    Optional<Station> findByNameIgnoreCase(String name);
    Optional<Station> findByStationID(long stationID);
}