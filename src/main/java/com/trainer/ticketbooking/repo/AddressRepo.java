package com.trainer.ticketbooking.repo;

import com.trainer.ticketbooking.entity.Address;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface AddressRepo extends CrudRepository<Address, Long> {
    Optional<Address> findByAddressID(long addressID);
}
