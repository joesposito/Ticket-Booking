package com.trainer.ticketbooking.repo;
import com.trainer.ticketbooking.entity.LocalUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface LocalUserRepo extends CrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByUsernameIgnoreCase(String username);

    Optional<LocalUser> findByUserID(long userID);

}
