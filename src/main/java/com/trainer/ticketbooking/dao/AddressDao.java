package com.trainer.ticketbooking.dao;

import com.trainer.ticketbooking.entity.Address;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface AddressDao extends CrudRepository<Address, Long> {
    Optional<Address> findByAddressID(long addressID);
}
